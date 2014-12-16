#!/bin/bash
MYSQL_DB=meanmcq
MYSQL_USER=mcq
MYSQL_PASS=mcq123
echo "Configuring MySql.."

mysql -u root -p -e "create database $MYSQL_DB; create user $MYSQL_USER@localhost identified by '$MYSQL_PASS'; grant all on $MYSQL_DB.* to $MYSQL_USER@localhost;"

# echo "Seeding database..."	
# mysql -u $MYSQL_USER -p$MYSQL_PASS meanmcq < db.sql