from tkinter import *
from ScrolledList import *
from Entry import *
import Products
from Code import *

global displayText
global options
global statusLabel

displayText = 'Ensure that the file name does not contain any spaces.\nIn case you want to add a section heading leave the file name blank.'
options=[]

def updateStatus(status):
    global statusLabel
    statusLabel['text']="Status:\t"+status
    statusLabel.update()


def generateOptions():
    global options
    options = []
    for each_product in Products.productList:
        options.append("\t  "+str(int(each_product.idno))+"    \t\t\t\t"+each_product.title+"    \t\t\t\t\t\t"+each_product.fname)


def onClickAdd():
    popup = Toplevel()
    popup.title("Add Item")
    entryWindow = EntryWindow(popup, button1Text='Add')
    popup.grab_set()
    popup.focus_set()
    popup.wait_window()
    if entryWindow.flag == TRUE :
        try:
            Products.addProduct(entryWindow.values[0], entryWindow.values[1], entryWindow.values[2])
            refresh()
            updateStatus("Item added successfully.")
        except ValueError as ve:
            updateStatus("Item addition failed. Invalid inputs.\n"+str(ve))
        except:
            updateStatus("Item addition failed.")


def onClickRemove():
    try:
        index = scrollListFrame.listbox.curselection()[0]
        if messagebox.askokcancel(title='Confirm action', message='Are you sure you wish to remove the selected product?') :
            Products.removeProductByIdno(index)
            refresh()
            updateStatus("Item removed successfully.")
    except IndexError:
        updateStatus("Please select an item.")
    except:
        updateStatus("Item remove failed.")


def onClickEdit():
    try:
        index = scrollListFrame.listbox.curselection()[0]
        values = [Products.productList[int(index)].idno, Products.productList[int(index)].title, Products.productList[int(index)].fname]
        popup = Toplevel()
        popup.title("Edit Item")
        entryWindow = EntryWindow(popup, button1Text='Edit', values=values)
        popup.grab_set()
        popup.focus_set()
        popup.wait_window()
        if entryWindow.flag == TRUE :
            Products.removeProductByIdno(index)
            Products.addProduct(entryWindow.values[0], entryWindow.values[1], entryWindow.values[2])
            refresh()
            updateStatus("Item edit successful.")
    except IndexError:
        updateStatus("Please select an item.")
    except:
        updateStatus("Item edit failed.")


def refresh():
    Products.calibrateIdno()
    scrollListFrame.listbox.delete(0,scrollListFrame.listbox.size())
    generateOptions()
    pos = 0
    for each_option in options:
        scrollListFrame.listbox.insert(pos,each_option)
        pos += 1


def onClickCompile():
    try:
        res = messagebox.askokcancel(title='Confirm action', message='Are you sure you wish to compile and generate the APK?\nThis action will save all the changes you have made!')
        if res:
            updateStatus("Build in progress. Please wait.\n\tThis might take a minute.")
            refresh()
            Products.writeProductsCSV()
            codeHandlers = []
#            codeHandlers.append(CodeHandler("IBApp.zip","12345"))
#            codeHandlers.append(CodeHandler('IndianBankApp.zip','qwerty'))
            codeHandlers.append(CodeHandler("IBMA_NavDrawer.zip","indianbank","com/example/ibapp/app"))
            codeHandlers.append(CodeHandler("IBMA_SplitScreen.zip","indianbank","com/example/indianbankapp/app"))
            CodeHandler.delAllPrevious()
            for each_codeHandler in codeHandlers:
                each_codeHandler.extractCode()
                each_codeHandler.copyAssets()
                each_codeHandler.updateCode()
 #               each_codeHandler.buildPackage()
            messagebox.showinfo(title='Process complete', message='Code prepared successfully.')
            updateStatus("Code Preparation Successful.")
    except Exception as e:
        updateStatus("Build failed.\n"+str(e))
    finally:
        pass
 #       CodeHandler.delAllPrevious()

def onClickRevertChanges():
    if messagebox.askokcancel(title='Confirm action', message='Are you sure you wish to revert all the changes you have made?') :
        try:
            Products.readProductsCSV()
            refresh()
            updateStatus("Revert successful.")
        except:
            updateStatus("Revert unsuccessful.")


def onClickSaveChanges():
    if messagebox.askokcancel(title='Confirm action', message='Are you sure you wish to save all the changes you have made?') :
        try:
            refresh()
            Products.writeProductsCSV()
            updateStatus("Changes saved successfully.")
        except:
            updateStatus("Changes could not be saved.")


def launchScreen():
    global root
    global scrollListFrame
    global statusLabel

    root = Tk()

    buttonFrame = Frame(root)

    generateOptions()
    scrollListFrame = ScrollList(options,root)
    detailsLabel = Label(root, text=displayText)
    statusLabel = Label(root, text='Status:')

    refresh()

    buttonAdd = Button(buttonFrame, text='Add', command=onClickAdd)
    buttonRemove = Button(buttonFrame, text='Remove', command=onClickRemove)
    buttonEdit = Button(buttonFrame, text='Edit', command=onClickEdit)
    #buttonRefresh = Button(buttonFrame, text='Refresh', command=refresh)
    buttonRevertChanges = Button(buttonFrame, text='Revert Changes', command=onClickRevertChanges)
    buttonSaveChanges = Button(buttonFrame, text='Save Changes', command=onClickSaveChanges)
    buttonCompile = Button(buttonFrame, text='Prepare Code', command=onClickCompile)

    buttonAdd.pack(side=TOP, pady=5, fill=X)
    buttonRemove.pack(side=TOP, pady=5, fill=X)
    buttonEdit.pack(side=TOP, pady=5, fill=X)
    #buttonRefresh.pack(pady=50, fill=X)
    buttonCompile.pack(side=BOTTOM, pady=5, fill=X)
    buttonSaveChanges.pack(side=BOTTOM, pady=5, fill=X)
    buttonRevertChanges.pack(side=BOTTOM, pady=5, fill=X)

    statusLabel.pack(side=BOTTOM, fill=X, anchor=S, pady=10)
    detailsLabel.pack(side=TOP, ipadx=10, ipady=25, fill=X)
    scrollListFrame.pack(side=LEFT, padx=10, fill=BOTH, expand=YES)
    buttonFrame.pack(side=RIGHT, ipadx=25, padx=10, fill=Y)

    root.title("IBMAC")
    root.minsize(width=900,height=550)
    w = root.winfo_screenwidth()
    h = root.winfo_screenheight()
    x = w/2 - 900/2
    y = h/2 - 550/2
    root.geometry("900x550+%d+%d" % (x, y))
    root.mainloop()


##TODO Remove line
Products.readProductsCSV()
launchScreen()
