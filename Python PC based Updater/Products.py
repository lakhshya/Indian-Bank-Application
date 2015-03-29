global productList
global csvFile
csvFilename='products.csv'
productList = []

class product:
    def __init__(self, idno, title, fname):
        try:
            self.idno = float(idno)
        except ValueError:
            raise ValueError("Invalid Id. No.")
        if title == '':
            raise ValueError("Title can't be empty")
        if fname.count(' ') !=0:
            raise ValueError("Filename should not cantain spaces")
        self.title = title
        self.fname = fname
    def display(self):
        print("Id. no. = " + str(self.idno) + "\t\tTitle = " + self.title + "\t\tFname = " + self.fname)
    def generateCSVtext(self):
        return str(self.idno)+','+self.title+','+self.fname


def readProductsCSV(filename=csvFilename):
    global productList
    productList = []
    with open(filename) as mfile:
        column_headings = mfile.readline().strip().split(',')
        for each_line in mfile:
            elements = each_line.strip().split(',')
            pr = product(elements[0], elements[1], elements[2])
            productList.append(pr)
    

def writeProductsCSV(filename=csvFilename):
    global productList
    with open(filename, 'w') as mfile:
        mfile.write("idno,title,fname\n")
        for each_product in productList:
            csvText = each_product.generateCSVtext()
            mfile.write(csvText + '\n')
    

def addProduct(idno, title, fname):
    global productList
    pr = product(idno, title, fname)
    productList.append(pr)


def removeProductByIdno(idno):
    idno = float(idno)
    for each_product in productList:
        if each_product.idno == idno:
            productList.remove(each_product)
        

def removeProductByTitle(title):
    for each_product in productList:
        if each_product.title == title:
            productList.remove(each_product)


def calibrateIdno():
    count = 0
    productList.sort(key = lambda x:x.idno, reverse = False)
    for each_product in productList:
        each_product.idno = count
        count = count+1
        

def displayProductList(disp=False):
    for each_product in productList:
        each_product.display()
    print("")
