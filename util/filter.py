import getpass
from util import bcolors
from time import sleep

def filter(arg):
    global args, verbose, file_path
    args = arg
    verbose, file_path = args.verbose, args.f
    if args.p == "": args.p = getpass.getpass("password: ")

    print(f"{bcolors.HEADER}[*] ip={args.i} port={args.P} username={args.u} password={'*' * len(args.p)}")
    print(f"[*] command={args.c} remove={args.remove} script={file_path} info={args.info} verbose=LVL{verbose}")

def motd(name):
    max_length = 60
    dashes = (max_length - len(name)) // 2
    
    if name != "none": print(f"{bcolors.HEADER}[+] {'-' * dashes} {name} {'-' * dashes}")
    else: print(f"{bcolors.HEADER}[+] {'-' * (max_length + 1)}")
    sleep(.10)