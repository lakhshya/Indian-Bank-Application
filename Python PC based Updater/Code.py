import Products

class CodeHandler:

    def __init__(self, zname, pwd, package):
        self.zname = zname
        self.pwd = pwd
        self.dir = zname.rsplit(sep='.',maxsplit=1)[0]
        self.fpath = "./androidCode/"+self.dir+"/app/src/main/java/"+package+"/content/AppContent.java"
    
    
    def updateCode(self, fpath=None):
        if fpath == None:
            fpath=self.fpath
#            fpath="./androidCode/"+self.dir+"/app/src/main/java/com/example/"+self.dir.lower()+"/app/AppContent.java"
        loc = -1
        with open(fpath,'r') as src:
            src.seek(0)
            fLine = src.readline()
            while fLine != '':
                if fLine.rfind('/*Add code here*/') != -1:
                    loc = src.tell()
                    break
                fLine = src.readline()
            remaining = src.read()
            
        with open(fpath,'a') as src:
            src.seek(loc)
            src.truncate()
            for each_product in Products.productList:
                if each_product.fname=='' :
                    src.write('\t\taddSection("'+each_product.title+'");\n')
                else :
                    src.write('\t\taddProduct("'+each_product.title+'", "'+each_product.fname+'");\n')
            src.write('\t\taddSection("Calculators");\n')
            src.write('\t\taddCalculator("EMI Calculator", AppContentCalculator.TYPE_EMI);\n')
            src.write('\t\taddCalculator("FD Calculator", AppContentCalculator.TYPE_FD);\n')
            src.write(remaining)


    def buildPackage(self):
        pass
        iEnviron = None
        import os,sys
        cwd = os.getcwd()
        sep = os.sep
        try:
            iEnviron = os.environ.__getitem__("ANDROID_HOME")
        except KeyError:
            os.environ.__setitem__("ANDROID_HOME", cwd+sep+r"android-sdks")
            print("Except block")
        finally:
            os.chdir(os.curdir+sep+r"androidCode"+sep+self.dir)
            command = '"'+cwd+sep+r"gradle-1.11"+sep+r"bin"+sep+r'gradle" clean build --offline'
            res =os.popen(command).readlines()
            print(res)
            os.chdir(cwd)
            if iEnviron != None:
                os.environ.__setitem__("ANDROID_HOME",iEnviron)
            else :
                os.environ.__delitem__("ANDROID_HOME")
            

    
    def extractCode(self):
        from zipfile import ZipFile
        zfile = ZipFile(self.zname)
        zfile.extractall(path='./androidCode/', pwd=self.pwd.encode('utf-8'))

    
    def copyAssets(self):
        import shutil
        try:
            shutil.rmtree('./androidCode/'+self.dir+"/app/src/main/assets")
        except:
            pass
        shutil.copytree(src='./assets/', dst="./androidCode/"+self.dir+"/app/src/main/assets")
        
        
    def delPrevious(self):
        try:
            import shutil
            shutil.rmtree('./androidCode/'+self.dir)
        except:
            pass

    
    def delAllPrevious():
        try:
            import shutil
            shutil.rmtree('./androidCode/')
        except:
            pass

#a=CodeHandler("IBApp","12345")
#a.buildPackage()
