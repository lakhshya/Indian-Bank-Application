from tkinter import *
fields = 'Id.no./Position', 'Title', 'File name'

class EntryWindow():
    
    def onClick1(self):
        self.flag=TRUE
        self.values=[]
        for entry in self.entries:
            self.values.append(entry.get())
        self.root.destroy()

    def onClick0(self):
        self.flag=FALSE
        self.values=[]
        self.root.destroy()

    def onReturn(self,event):
        self.onClick1()

    def __init__(self, root, fields=fields, button1Text="Ok", button0Text="Cancel", values=[]):
        if values.__len__() == 0:
            values=['','','']
        entries = []
        self.root=root
        self.flag = FALSE
        for field in fields:
            row = Frame(root)
            lab = Label(row, text=field, width=20)
            ent = Entry(row)
            ent.insert(0,values.pop(0))
            row.pack(side=TOP, fill=X, padx=10, pady=10, expand=YES)
            lab.pack(side=LEFT, padx=10)
            ent.pack(side=RIGHT, expand=YES, fill=X)
            ent.bind('<Return>',self.onReturn)
            entries.append(ent)
        row = Frame(root)
        button1 = Button(row, text=button1Text, command=self.onClick1)
        button0 = Button(row, text=button0Text, command=self.onClick0)
        button1.pack(side=RIGHT, padx=10)
        button0.pack(side=RIGHT, padx=10)
        row.pack(side=BOTTOM, fill=X, padx=10, pady=10, expand=YES)
        self.entries = entries
