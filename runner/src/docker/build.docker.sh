#!/bin/bash

apt-get update
apt-get install -y build-essential chrpath libssl-dev libxft-dev

apt-get install -y libfreetype6 libfreetype6-dev
apt-get install -y libfontconfig1 libfontconfig1-dev
apt-get install -y imagemagick

curl -sL https://deb.nodesource.com/setup_11.x | bash -
apt-get install -y npm
apt-get install -y nodejs
cd /scripts
npm install image-diff

cd ~
export PHANTOM_JS="phantomjs-2.1.1-linux-x86_64"
wget https://bitbucket.org/ariya/phantomjs/downloads/$PHANTOM_JS.tar.bz2
tar xvjf $PHANTOM_JS.tar.bz2

mv $PHANTOM_JS /usr/local/share
ln -sf /usr/local/share/$PHANTOM_JS/bin/phantomjs /usr/local/bin

mkdir /output
mkdir /baseline