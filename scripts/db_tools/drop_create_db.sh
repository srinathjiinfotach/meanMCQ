#!/bin/bash
MYSQL_DB=meanmcq
echo "Dropping and creating database 'meanmcq'"
mysql -u root -p -e "drop database $MYSQL_DB;create database $MYSQL_DB;"
echo "Operations completed"