############################################
## ShuaiQi's Project
## Date 	01-04-2016
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


table <- read.table("exon_level_process_v2.txt")
## table <- read.table("D:/GitHub/Stats/Data_Analysis_Duke/SQProject/exon_level_process_v2.txt")
# table<-read.table("C:/Users/shuaiqi/Desktop/duke/Andrew/data/for_asa/other_stuff/exon_level_process_v3.txt")


## assign column names
colnames(table) <- c("chr", "gene", "dom", "subdom", "exon", "gene.dom", 
             "gene.dom.subdom", 
             "envarp",    # pass
             "envarpf",   # pass functional
             "envarpfr",  # pass functional rare
             "emutr")     # mutation rate 


## attract data from the original table??
table<-within(table,envarpfc<-envarpf-envarpfr)#y

table<-within(table,gene<-factor(gene))

table<-within(table,gene.dom<-factor(gene.dom))

table<-within(table,gene.dom.subdom<-factor(gene.dom.subdom))



## subset the table/genes
## table<-table[1:100000,]    ##when we do not run this line, we run the whole genes

#for the use of counting number of gene
sumenvarp<-aggregate(table$envarp, by=list(Category=table$gene), FUN=sum)

sumenvarpfc<-aggregate(table$envarpfc, by=list(Category=table$gene), FUN=sum)[,2]

## simplify table1
table1<-data.frame(cbind(sumenvarp,sumenvarpfc))


## assign column names to table1
colnames(table1)<-c("gene","sumenvarp","sumenvarpfc")





#######################################################
## Part Two
## Call Stan to do the simutation
#######################################################


#install.packages("rstan")

library("rstan")

## the following two lines would help the parallel computing
rstan_options(auto_write = TRUE)
options(mc.cores = parallel::detectCores())

##gene_code


########################################################
## scale_d talbe
########################################################
scale_d=table1[which(table1$sumenvarp!=0), ]
scale_d$normx <- c( scale(scale_d$sumenvarp) )


######################
# init the parameters
######################
#N <- dim(table)[1]
J <- dim(scale_d)[1]                       #number of genes in table1
gene <- as.numeric(table$gene)
genelevel <- length(unique(gene))
index<-match(gene, unique(gene)) 




M1_table<-list( J=J,		             
		gene=c(1:length(scale_d$sumenvarpfc)),
		x=scale_d$normx,
		y=scale_d$sumenvarpfc
		)


## fit rstan()

## fit the model
fit0 <- stan(file = "possion.simpgene.rstan.stan")

## fit the model with data

fit1 <- stan(	fit = fit0, 
		data = M1_table, 
		iter = 100000, 
		warmup = 50000,
		chains=4
		)


print(fit1, "a")

print(fit1, "beta")



answer1 <- extract(fit1, permuted = TRUE)
effect <- answer1$a
write.table(effect, "01262_Allgene_effectstan100k.txt", sep="\t")


## check convergence 
pdf("01262_Allgene_traceplot100k.pdf")


traceplot(fit1,"beta")
plot(density(answer1$beta), xlab="beta", main="distribution of beta")

## traceplot(fit1, "a", ncol=2, nrow=2)
dev.off()

##################
## END 
##################