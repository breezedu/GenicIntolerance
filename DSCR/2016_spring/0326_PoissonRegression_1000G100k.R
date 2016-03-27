############################################
## ShuaiQi's Project
## Date 	03-25-2016
## Aim: 	Try All Genes
## @ authors: 	SQ
## Data source: /dscrhome/gd44/SQProject/RStan/2016/exon_level_process_v2.txt
## Models: 	Bayesian Stan
## Parameters:	
## Outputs: 	


## Data source and copyright?
## Read in table from local hard drive:
## setup the working directory to where exon_level_process_v2.txt locates

##########################################
## Part One Read in the data
## create the tables
##########################################

## Read in exon level data from the same directory
table <- read.table("exon_level_process_v2.txt")
#obs<-c(1:dim(table)[1])
#table<-cbind(obs,table)
#table<-read.table("C:/Users/shuaiqi/Desktop/duke/Andrew/data/for_asa/other_stuff/exon_level_process_v3.txt")

colnames(table)<- c("chr", "gene", "dom", "subdom", "exon", "gene.dom", 
             "gene.dom.subdom", 
             "envarp",    # pass
             "envarpf",   # pass functional
             "envarpfr",  # pass functional rare
             "emutr")     # mutation rate 

table<-within(table,envarpfc<-envarpf-envarpfr)#y
table<-within(table,gene<-factor(gene))
table<-within(table,gene.dom<-factor(gene.dom))
table<-within(table,gene.dom.subdom<-factor(gene.dom.subdom))
table<-table[which(table$envarp!=0), ]
table$x=scale(table$envarp)
table<-table[1:1000,]


#for the use of counting number of gene
sumenvarp<-aggregate(table$envarp, by=list(Category=table$gene), FUN=sum)
sumenvarpfc<-aggregate(table$envarpfc, by=list(Category=table$gene), FUN=sum)[,2]
table1<-data.frame(cbind(sumenvarp,sumenvarpfc))
colnames(table1)<-c("gene","sumenvarp","sumenvarpfc")







#######################################################
## Part Two
## Call Stan to do the simutation
#######################################################

hiernormalinvg<-"
data{ #get the data set 
int<lower=0> N;   # number of exon level
int<lower=0> J;   # number of gene level
int <lower=1,upper=J> gene[N];  #index of gene
int <lower=1,upper=N> exon[N];  #index of exon 
vector[N] xij;   #x at exon level
vector[N] yij; #y at exon level
}
parameters{ #specify the parameter we want to know 
real beta;  #common slope for the exon level
real mu;      #common intercept for the exon level
vector[N] aij; #random intercept for the exon level
real <lower=0> sigma_aj2[J];  #variance of intercept at exon level 
vector[J] aj; #random intercept for the gene level 
real <lower=0> sigma_a;  #variance of intercept at gene level
real <lower=0> sigma; #variance of yij
real <lower=0> eps; #hyperparameter for sigma_aj
}
transformed parameters{ #specify the model we will use 
}
model { #give the prior distribution 
   vector[N] lambdaij; #exon level model
   for (i in 1:N)
   lambdaij[i] <- mu+beta*xij[i]+aij[exon[i]]+aj[gene[i]];#specify the gene group
   beta ~normal(0,1);
   sigma ~ uniform(0, 20);
   sigma_a ~uniform(0,10);
   eps ~ inv_gamma(0.1,0.1);
   sigma_aj2 ~inv_gamma(eps,eps);
   aj ~ normal(0, sigma_a);
   yij ~ normal(lambdaij,sigma);
   for (i in 1:N)
       aij[i]~ normal(0,sqrt(sigma_aj2[gene[i]]));
}
"




#################################################
## call RStan package
#################################################

library("rstan")


J<-dim(table1)[1] #gene number 
N<-dim(table)[1]  #exon number 
xij=c(table$envarp)
xj=table1$sumenvarp
yij=table$envarpfc
yj=table1$sumenvarpfc

gene<-as.numeric(table$gene) #list
genelevel<-length(unique(gene)) #number
indexg<-match(gene, unique(gene))  #list
exon<-c(1:length(table$envarpfc))

M1_table<-list(N=N, J=J, xij=xij,
yij=yij,gene=indexg, exon=exon)

control=list(adapt_delta=0.99,max_treedepth=12)



fitinv <- stan(model_code=hiernormalinvg, data=M1_table,iter=2000,warmup=1900,chains=4)




#####################################################################
## Print fit and alpha/beta
######################################################################################################

print(fitinv, beta)


######################################################################################################
#plot the density of parameters
######################################################################################################
#sigma_aj,sigma_a,sigma^2,eps

answer<-extract(fitinv,permuted=TRUE)
plotdesvar<-function(length){
	pdf(file = "inverse gamma prior variance density plot.pdf")
	for (i in 1:length){
		plot(density(answer$sigma_aj[,i]),main=c("density plot of exon-level variance",i))
		}
	plot(density(answer$sigma_a),main="density plot of sigma_a")
	plot(density(answer$sigma2),main="density plot of sigma^2")
	plot(density(answer$eps),main="density plot of hyperparameter")
    dev.off()
    
}
plotdesvar(J)

#aij,beta,mu
plotdesint<-function(N){
	pdf(file = "inverse gamma aij,mu,beta density plot")
	for (i in 1:N){
		plot(density(answer$aij[,i]),main=c("density plot of exon-level intercept",i))
		}
    plot(density(answer$beta),main="density plot of beta")
	plot(density(answer$mu),main="density plot of mu")	
    dev.off()
    
}
plotdesint(N)

#aj 
plotdes<-function(length){
	pdf(file = "inverse gamma aj density plot")
	for (i in 1:length){
		plot(density(answer$aj[,i]),main=c("density plot of gene-level variance",i))
		}
    dev.off()
    
}


plotdes(J)



##################
## END 
##################