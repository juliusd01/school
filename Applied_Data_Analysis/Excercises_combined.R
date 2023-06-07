install.packages("tidyverse")
install.packages("factor")
install.packages("readxl")
library(tidyverse)
library(factor)
library(readxl)

# Excercise 1
getwd()
read_excel("../data")

head(data)

data <- replace(data, data==0, NA)
data
max(data["age"])
min(data["age"])

levels(data["gender"])
factors <- factor(c("male", "female", "diverse"))
levels(data$gender)

head(data)

# Excercise 2
data$gender <- factor(data$gender)
levels(data$gender) <- c("male", "female", "diverse")

data$employment <- factor(data$employment)
levels(data$employment) <- c("student", "employed", "unemployed")

data$education <- factor(data$education)
levels(data$education) <- c("no degree", "secondary", "intermediate", "high school", "academic")

data$play_frequency <- factor (data$play_frequency)
levels(data$play_frequency) <- c("never", "every feew months", "every few weeks", "1-2 days a week", "3-5 days a week", "daily")

data$treatment <- factor(data$treatment)
levels(data$treatment) <- c("control", "lootbox in task reward", "lootbox picture", "badge")

subsetControl <- subset(data12, data12$treatment=="control")
subsetLootPic <- subset(data12, treatment=="lootbox picture")
subsetLootInTask <- subset(data12, treatment="lootbox in task reward")
subsetBadge <- subset(data12, treatment="badge")

summary(subsetControl)
summary(subsetLootPic)

#Excercise 3
subYoung <- data12%>%
  filter(old==FALSE)

subOld <- data12%>%
  filter(old==TRUE)

t.test(data12$`total time`, alternative = "two.sided", mu = 320000, conf.level = 0.95, paired=FALSE)

t.test(subOld$`total time`, alternative = "two.sided", mu = 320000, conf.level = 0.95)

t.test(subYoung$`total time`, alternative = "two.sided", mu = 320000, conf.level = 0.95)
#A bit skewed for the age subsets but they would all fail to reject the Null hypothesis

#7.2
#1. H0: Old and Young people completed an equal amount of tasks || H1: Young people completed less tasks than older people

t.test(subYoung$tasks_completed,subOld$tasks_completed, alternative = "less", mu = 0, conf.level = 0.95)
#we fail to reject the H0, because p>0.05

#Comparing Lootbox with pictures-group and Control Group
#1. H0: Both groups completed the same amount of tasks H1: No equal amount of tasks were completed

t.test(subsetLootPic$tasks_completed, subsetControl$tasks_completed, alternative = "two.sided", mu=0, conf.level = 0.95)
#Reject H0

#Pairwise plot
pairwise.t.test(x=data12$tasks_completed, g=data12$treatment, p.adjust.method = "none", pool.sd = FALSE, alternative = "two.sided")

#7.3 Because we do not have panel data and hence we do not compare the same people across time

#8
ggplot(data12, aes(`total time`))+
  geom_histogram(aes(y=..density..))+
  stat_function(
    fun = dnorm,
    args = list(mean = mean(data12$`total time`),
                sd = sd(data12$`total time`))
  )
#Je nach bins kann man sagen, dass totaltime mit Fantasie normalverteilt ist.

#8.2
ggplot(data12, aes(x=log(`total time`)))+
  geom_histogram(aes(y=..density..))+
  stat_function(
    fun = dnorm,
    args = list(mean = mean(log(data12$`total time`)),
                sd = sd(log(data12$`total time`)))
  )
#Okay, das sieht geil aus. Definitiv normalverteilt

#8.3
ggplot(data, aes(sample=(`total time`)))+
  geom_qq()+
  geom_qq_line()

#Excercise 4

#H0: The means are the same. H1: There is a significant difference between the means in tasks completed of the groups.

aov_tc_treatment <- aov(data12$tasks_completed~data12$treatment)
summary(aov_tc_treatment)
#Große Unterschiede zwischen den Werten. Kann ich aber nicht wirklich interpretieren.

pairwise.t.test(data12$tasks_completed, data12$treatment, p.adjust.method = "none", alternative = "two.sided")
#correct
pairwise.t.test(data12$tasks_completed, data12$treatment, p.adjust.method = "bonferroni", alternative = "two.sided")
#correct
pairwise.t.test(data12$tasks_completed, data12$treatment, p.adjust.method = "holm", alternative = "two.sided")
#correct
#Bonferroni correction is recommended. If it gives you shitty results, take Holm.

#Task 11 - Checking the assumption of ANOVA

summary_tasks_completed <- data12%>%
  group_by(treatment)%>%
  summarise(mean=mean(tasks_completed), median=median(tasks_completed), sd=sd(tasks_completed))

View(tasks_completed)
minmax <- summary_tasks_completed%>%
  select(sd)%>%
  summarise(min=min(sd)**2, max=max(sd)**2)
#11.8(lowest) and about 20(highest)
20/11.8
#1.7<2

install.packages("car")
library(car)
leveneTest(data12$tasks_completed, data12$treatment, center = median)

#Welch-ANOVA
oneway.test(data12$tasks_completed~data12$treatment, var.equal = FALSE)


ggplot(data12, aes(sample = tasks_completed))+
  geom_qq()+
  geom_qq_line()

kruskal.test(data12$tasks_completed, data12$treatment)

#small p value -> significant differences in the means of tasks completed in the groups 

#Excercise 5

ggplot(data=data12, aes(x=age, y=data12$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_tt_age <- lm(data12$total_time ~ data12$age)
summary(lm_tt_age)
#low valuie of R: high unexplained variation -> model is no good

ggplot(data=subsetControl, aes(x=age, y=subsetControl$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_sc_age <- lm(subsetControl$total_time~subsetControl$age)
summary(lm_sc_age)

#Same for explanatory variable tasks_completed
ggplot(data=data12, aes(x=tasks_completed, y=data12$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_tt_age <- lm(data12$total_time ~ data12$tasks_completed)
summary(lm_tt_age)

ggplot(data=subsetControl, aes(x=tasks_completed, y=subsetControl$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_sc_age <- lm(subsetControl$total_time~subsetControl$tasks_completed)
summary(lm_sc_age)


#same for explanatory variable rt0
ggplot(data=data12, aes(x=rt0, y=data12$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_tt_age <- lm(data12$total_time ~ data12$rt0)
summary(lm_tt_age)

ggplot(data=subsetControl, aes(x=rt0, y=subsetControl$total_time))+
  geom_point()+
  geom_smooth(method="lm")

lm_sc_age <- lm(subsetControl$total_time~subsetControl$rt0)
summary(lm_sc_age)

#13.1
lm_tt_age_rt0 <- lm(data12$total_time~data12$age+data12$rt0)
summary(lm_tt_age_rt0)

#13.2
lm_tt_age_rt0_interaction <- lm(formula = data12$total_time ~ data12$age + data$rt0 + data12$age:data12$rt0)
summary(lm_tt_age_rt0_interaction)

#13.3
lm_tt_treatment_rt0 <- lm(data12$total_time~ data12$treatment + data12$treatment:data12$rt0)
summary(lm_tt_treatment_rt0)

