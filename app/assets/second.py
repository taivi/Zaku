# -*- coding: UTF-8 -*-
import json
def processJson(inputJsonFile, outputJsonFile):
    fout = open(outputJsonFile, 'w')
    fin = file(inputJsonFile)
    s = json.load(fin)
    times = 0
    while times < len(s):
        print s[times].get("b", "false")
        outStr = s[times]["title"] + "|" + s[times].get("b", "false")
        fout.write(outStr.strip().encode('utf-8') + '\n')
        times = times + 1

processJson('b.json', 'myOutput.json')
