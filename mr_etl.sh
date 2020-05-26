#!/bin/bash

source ~/.bash_profile
if [$#!=1];then
echo "Usage: mr_etl.sh <dateString>"
echo "E.g.: mr_etl.sh 20200202"

exit 1;
fi

process_date=$1

echo -e "\033[36m###### step1:MR ETL ######\033[0m"
hadoop jar /home/samzhong17/hadoopMRETL/HadoopMRETL.jar LogETLDriver /user/samzhong17/hadoop-mr-etl/log/* /user/samzhong17/hadoop-mr-etl/mr_output/day=$process_date

hive -e "use hive;
create external table if not exists mr_etl1(
cdn string,
region string,
level string,
time string,
ip string,
domain string,
url string,
traffic bigint
)
partitioned by (day string)
row format delimited fields terminated by '\t'
location '/user/samzhong17/hadoop-mr-etl/hive_table/mr_etl1';"

echo -e "\[36m###### step2:Mv Data to DW ###### \033[0m"
hadoop fs -mv /user/samzhong17/hadoop-mr-etl/mr_output/* /user/samzhong17/hadoop-mr-etl/hive_table/mr_etl1

echo -e "\033[36m###### step3:Alter metadata ######\033[0m"
database=mr_etl
