# Purpose

A service for indexing and finding media (photos, video, music, etc.) easily.

# Running it locally

## MySQL Server

### Install

1. See [https://dev.mysql.com/doc/refman/8.0/en/installing.html](https://dev.mysql.com/doc/refman/8.0/en/installing.html) for more details, but if you're on a system that has a package manager, this should be pretty simple to do. On MacOS for example, you should be able to just use `brew install mysql` (see [https://brew.sh](https://brew.sh))

### Configure

1. Edit your mysql.cnf file to allow TCP connections by changing the `bind-address`. The file is typically found in the `/etc/mysql/` directory, but can be different depending on your package mangager or how you installed it. Find edit the file and then find the section `mysqld` and ensure the `bind-address` looks like the following:
```
[mysqld]
bind-address = 0.0.0.0
```
2. Start and connect:
```
mysqld &
mysql -uroot
```
3. Create the database schema:
```
create database mediafinder;
```
4. Create a user with a password (remember for later):
```
create user 'mediafinder'@'%' identified by 'password';
```
5. Grant permissions to the new user:
```
grant all on mediafinder.* to 'mediafinder'@'%';
```
6. Exit:
```
exit
```
7. Set the mediafinder database password environment variable to the password from 4:
```
export MEDIAFINDER_PW=password
```
You can add this to your bashrc as well, so you don't have to do it again after restarting your shell:
```
echo 'export MEDIAFINDER_PW=password' >> ~/.bashrc
```

### Test

1. Connect to test:
```
mysql -h127.0.0.1 -umediafinder -p
```
2. Show the databases available to the `mediafinder` user. It should have access to the `mediafinder` schema:
```
show databases;
```
3. Exit:
```
exit
```

## Elasticsearch Server

### Install

1. Probably easiest to just follow [https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html](https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html). Elasticsearch comes with its own JDK, so it isn't necessary to have a paricular version installed.

### Test

1. Onces the server is up you should be able to go to [https://localhost:9200](https://localhost:9200) and see the information about the instance.

## Running the application

### Install Java JDK 16

1. Either use a package manger (like brew) to install and configure JDK 16 or download it directly from [https://jdk.java.net/16/](https://jdk.java.net/16/). The following steps assume you are downloading it and installing it manually.
2. Navigate to a directory you want to keep the JDK in:
```
cd ~
```
3. Download JDK 16 for your OS:
MacOS
```
wget https://download.java.net/java/GA/jdk16/7863447f0ab643c585b9bdebf67c69db/36/GPL/openjdk-16_osx-x64_bin.tar.gz 
```
Linux
```
wget https://download.java.net/java/GA/jdk16/7863447f0ab643c585b9bdebf67c69db/36/GPL/openjdk-16_linux-x64_bin.tar.gz
```
4. Extract the files:
```
tar xvf openjdk-16_*.tar.gz
```
5. Navigate to the directory:
```
cd openjdk-16_*
```
6. Export the JDK bin PATH and set JAVA_HOME:
```
echo 'export PATH="'$(pwd)'/bin:$PATH"' >> ~/.bashrc
echo 'export JAVA_HOME="'$(pwd)'"' >> ~/.bashrc
source ~/.bashrc
```
7. The following should return references to java 16:
```
java -version
```

### Building

1. Go to a projects directory of your choosing
2. Clone the repo
```
git clone https://github.com/kiLLeen/mediafinder.git
cd mediafinder
```
3. Build the application
```
./gradlew clean build jar
```

### Deploying

```
java -jar ./build/libs/mediafinder-*.jar
```

