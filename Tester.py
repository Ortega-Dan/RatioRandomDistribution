import os
stream = os.popen('ps aux|grep nvidia-powerd|awk \'{print $3}\'')
output = stream.readline()
stream.close()
print(output)

output = float(output)

if output > 7:
    print("ALERT")
else:
    print("FINE")
