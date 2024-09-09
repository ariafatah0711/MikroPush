import paramiko, paramiko.ssh_exception
from pathlib import Path
from time import sleep
from socket import error as socket_error

# util
from util import bcolors, banner, getArgument, filter, motd

class mikroPush:
    def __init__(self, args):
        # host
        self.host = {
            "hostname":args.i,
            "port":int(args.P),
            "username":args.u,
            "password":args.p,
            "timeout":5,
        }
        self.ssh = paramiko.SSHClient()
        self.ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())

        # connection ssh
        self.login()
        self.error, self.count, self.last = [0, 0, 0]
    
    def login(self):
        try:
            self.ssh.connect(**self.host)
            print(f"{bcolors.OKGREEN}[+] try connect to {self.host['hostname']}")
            sleep(0.5)
            print(f"{bcolors.OKGREEN}[+] login berhasil\n{bcolors.ENDC}")
            if not args.c and not args.s and not args.f and not args.info and not args.remove: print(f"{bcolors.ORANGE}[-] pilih salah satu argument mode yang akan digunakan: {bcolors.ENDC}\n-c 'command' \n-s script.txt, \n-f dir, \n--info, \n--remove"); exit()
        except paramiko.SSHException:
            print(f"{bcolors.FAIL}[-] Incorrect password")
            self.close()
        except (paramiko.SSHException, paramiko.ssh_exception.NoValidConnectionsError, socket_error) as e:
            print(f"{bcolors.FAIL}[-] Error: {str(e)}")
            self.close()
    
    def runCommand(self, command = ""):
        stdin, stdout, stderr = self.ssh.exec_command(command)
        self.output(stdout, command)
        print("\n", end="")

    def runScript(self, file):
        self.error, self.count, self.last = [0, 0, 0]
        sleep(.75)
        try:
            script = open(file, "r").readlines()
            for line, command in enumerate(script, start=1):
                if (command.startswith("# ") or command.startswith("; ") or not command.strip()): continue
                stdin, stdout, stderr = self.ssh.exec_command(command.strip())
                self.output(stdout, command, line)
                self.count += 1
            
            self.footer()
        except FileNotFoundError:
            print(f"{bcolors.ORANGE}[-] file script tidak ditemukan \n{bcolors.ENDC}")
            self.close()

    def output(self, stdout, command, line = 0):
            outputStdout = stdout.readlines()
            if len(outputStdout) == 0 and verbose > 0:
                print(f"{bcolors.OKGREEN}[✓] {bcolors.ORANGE}{command}{bcolors.ENDC}", end="")
                self.last = 1
                sleep(.10)
            elif len(outputStdout) == 1:
                print(f"{bcolors.FAIL}[☓] {bcolors.ORANGE}{command}{bcolors.ENDC} | line {line}: {outputStdout[0]}", end="")
                self.error += 1
                self.last = 2
                sleep(.15)
            elif len(outputStdout) > 1 and verbose > 0:
                print(f"{bcolors.HEADER}[O] {bcolors.ORANGE}{command}{bcolors.ENDC}", end="")
                if verbose >= 2: print("".join(outputStdout).lstrip(), end="")

                self.last = 3
                sleep(.25)
            else:
                pass
            return self.error
    
    def footer(self):
        success = self.count - self.error
        per = success / self.count * 100
        printerror = f"{bcolors.FAIL}{self.error} error {bcolors.ENDC}"
        
        if self.last == 1: print("\n\n", end="")
        elif self.last == 2 or (verbose >= 1 and self.last == 3): print("\n", end="")
        else: pass

        if per == 100:
            print(f"{bcolors.OKGREEN}[!] Task Compleate {bcolors.OKGREEN}{int(per)}%")
        elif per >= 75:
            print(f"{bcolors.OKGREEN}[!] Task Compleate {bcolors.HEADER}{int(per)}% {bcolors.ENDC}with {printerror}")
        else:
            print(f"{bcolors.OKGREEN}[!] Task Compleate {bcolors.FAIL}{int(per)}% {bcolors.ENDC}with {printerror}")

        motd("none")
        print("\n", end="")
    
    def close(self):
        self.ssh.close()
        exit()

if __name__ == "__main__":
    banner()
    
    args = getArgument()
    filter(args)
    
    # scripts
    list_name = ["remove", "info"]
    list_verbose = [1, 2]

    current_dir = Path(__file__).parent
    list_path = [
        current_dir.joinpath("./scripts/remove.sc"),
        current_dir.joinpath( "./scripts/info.sc")
    ]

    def pushCommand(command, v):
            global verbose
            verbose = v
            motd(f"run command")
            host.runCommand(command)

    def pushScript(name, path, v):
        global verbose, file_path
        verbose, file_path = v, path
        
        motd(f"{name} configuration!")
        host.runScript(file_path)

    def pushFolder(path, verbose):
        folder = Path(path)
        if not folder.is_dir(): print(f"[-] {bcolors.FAIL}Folder {path} tidak ditemukan{bcolors.ENDC}"); exit()
        
        for script_file in folder.glob("*.sc*"):
            pushScript(script_file, script_file, verbose)
    
    # custom priority running
    host = mikroPush(args)
    if args.remove: 
        print(f"{bcolors.ENDC}[ --remove ]")
        pushScript(list_name[0], list_path[0], list_verbose[0])
    if args.c: 
        print(f"{bcolors.ENDC}[ -c {args.c} ]")
        pushCommand(args.c, 2)
    if args.s: 
        print(f"{bcolors.ENDC}[ -s {args.s} ]")
        pushScript("custom script", args.s, args.verbose)
    if args.f:
        print(f"{bcolors.ENDC}[ -f {args.f} ]")
        pushFolder(args.f, args.verbose)
    if args.info:
        print(f"{bcolors.ENDC}[ --info ]")
        pushScript(list_name[1], list_path[1], list_verbose[1])

    host.close()