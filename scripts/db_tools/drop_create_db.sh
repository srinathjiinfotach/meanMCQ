#!/bin/bash
MYSQL_DB=meanmcq
mysql -u root -p -e "drop database $MYSQL_DB;create database $MYSQL_DB;"