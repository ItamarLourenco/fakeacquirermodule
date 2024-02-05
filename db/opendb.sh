#!/bin/bash
adb shell "run-as com.zigpay.fakeacquirerproject cat /data/data/com.zigpay.fakeacquirerproject/databases/fake_acquirer" > fake_acquirer;
adb shell "run-as com.zigpay.fakeacquirerproject cat /data/data/com.zigpay.fakeacquirerproject/databases/fake_acquirer-shm" > fake_acquirer-shm;
adb shell "run-as com.zigpay.fakeacquirerproject cat /data/data/com.zigpay.fakeacquirerproject/databases/fake_acquirer-wal" > fake_acquirer-wal;

sqlite3 fake_acquirer "$1" ;
