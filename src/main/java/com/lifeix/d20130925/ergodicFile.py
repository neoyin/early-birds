'''
Created on 2013-9-25

@author: Lifeix
'''
# import os
# fileList = []
# rootdir = 'I:/11'
# for root, subFolders, files in os.walk(rootdir):
#     print '\n'
#     print root
#     print subFolders
#     print files
#     print '\n'
#     for file in files:
#         if file.find('.csv') != -1:
#             file_dir_path = os.path.join(root,file)
#             fileList.append(file_dir_path)  
# print fileList

# a = [('2011-03-17', '2.26', 6429600, '0.0'), ('2011-03-16', '2.26', 12036900, '-3.0'),('2011-03-15', '2.33', 15615500,'-19.1')]
# print a
# b = sorted(a, key=lambda x:(x[1],x[2]),reverse=True)
# print b

import os,zipfile
fileList = []
rootdir = 'I:\PracticeSrc\lifeix-growth-road';
for root, subFolders, files in os.walk(rootdir):
    for file in files:
        if file.endswith('.class') and '\\com\\' in root:
            file_dir_path = os.path.join(root,file)
            fileList.append(file_dir_path)
        elif file.endswith('.jar'):
            zipFile = zipfile.ZipFile(os.path.join(root,file))
            for f in zipFile.namelist():
                print f
                if f.endswith('.class') and 'com/' in root:
                    file_dir_path = os.path.join(root,file)
                    fileList.append(file_dir_path)
print len(fileList)

# print os.path
             
            