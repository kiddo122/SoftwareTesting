{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;\csgray\c100000;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Instructions to run tests on your own computer:\
- Each of the test suites have a @BeforeClass function. Inside is System.setProperty(), with the second argument being a path.\
- There is a file located in selenium-testcases/src/main/resources. the path you need to use for this second argument is \'93/path/to/this/repo/selenium-testcases/src/main/resources/chromedriver\'94 (if on Mac, on windows add .exe).\
- You can now run the tests!}