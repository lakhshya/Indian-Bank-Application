from tkinter import *

class ScrollList(Frame):
    def __init__(self, options, parent=None):
        Frame.__init__(self, parent)
        self.makeWidgets(options)

    def handleList(self, event):
        index = self.listbox.curselection()
        label = self.listbox.get(index)
        self.runCommand(label)

    def makeWidgets(self, options):
        sbary = Scrollbar(self, orient='vertical')
        sbarx = Scrollbar(self, orient='horizontal')
        menuLabel = Label(self, justify=LEFT, text="Id./Position\tTitle\t\t\t\tResource file name")
        list = Listbox(self, relief=SUNKEN)
        sbary.config(command=list.yview)
        sbarx.config(command=list.xview)
        list.config(yscrollcommand=sbary.set, xscrollcommand=sbarx.set)
        menuLabel.pack(side=TOP)
        sbary.pack(side=RIGHT, fill=Y)
        sbarx.pack(side=BOTTOM, fill=X)
        list.pack(side=LEFT, expand=YES, fill=BOTH)
        pos = 0
        for label in options:
            list.insert(pos, label)
            pos += 1
        #list.config(selectmode=SINGLE, setgrid=1)
        list.bind('<Double-1>', self.handleList)
        self.listbox = list
        
    def runCommand(self, selection):
        print('You selected:', selection)
