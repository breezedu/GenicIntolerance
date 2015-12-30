##
##
## Homozygosity Project
## Date 12-29-2015
## Aim: Plot Loss of Homozygosity
## @ authors: Andrew Allen 
## @ student: Jeff Du
## Data source: 
## Models:  Poisson
## Parameters: 


## not sure where to get this vector
pwr.strat<-c(0.01,0.07,0.23,0.52,0.79,.97)
pwr.hom<-c(0.07,0.15,0.34,0.55,.8,.99)
bta<-c(1,.85,.65,.5,.35,.15)


## plot the homogeneous and stratified line
plot(1/bta,pwr.strat,type="l",col="red",xlab="1/beta",ylab="power",lwd=2,ylim=c(0,1))

lines(1/bta,pwr.hom,lwd=2)

## add figure legend
legend(4,.45,c("Homogeneous","Stratified"),fill=c("black","red"))


## one of the output plot figure could be found in the github repository:
## https://github.com/breezedu/GenicIntolerance/blob/master/HomozygosityMutations/Rplot_loss_of_homozygosity.png