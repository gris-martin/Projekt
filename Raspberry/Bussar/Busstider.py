#!/usr/bin/env python
# SL Realtidsinformation API: 55eb715251504b278db9b8100c7100e7
# SL Platsuppslag API: 3b15957b7590477fac169c212986f1e1
# Kungshamra siteid: 3433
# Bergshamra siteid: 9202

import urllib2
import json

api_key = '55eb715251504b278db9b8100c7100e7'
kungshamra = 3433;
bergshamra = 9202;

def bus_search(siteid):
    url = 'http://api.sl.se/api2/realtimedepartures.json?key=' + str(api_key) + '&siteid=' + str(siteid) + '&timewindow=30'
    json_obj = urllib2.urlopen(url)
    data = json.load(json_obj)
    resp = data['ResponseData']
    print 'Bussavgangar'
    for item in resp['Buses']:
        print "%s: %s" % (item['LineNumber'], item['DisplayTime'])

def sub_search(siteid):
    url = 'http://api.sl.se/api2/realtimedepartures.json?key=' + str(api_key) + '&siteid=' + str(siteid) + '&timewindow=30'
    json_obj = urllib2.urlopen(url)
    data = json.load(json_obj)
    resp = data['ResponseData']
    print 'Bussavgangar'
    for item in resp['Metros']:
        print "%s: %s" % (item['LineNumber'], item['DisplayTime'])

    print '\nTunnelbaneavgangar'
    for item in resp['Buses']:
        print "%s: %s" % (item['LineNumber'], item['DisplayTime'])

bus_search(kungshamra)
print "\n"
sub_search(bergshamra)

