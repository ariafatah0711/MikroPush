from util.bcolors import bcolors

# banner = 
class app:
    logo = '''
        ███╗   ███╗██╗██╗  ██╗██████╗  ██████╗ ██████╗ ██╗   ██╗███████╗██╗  ██╗
        ████╗ ████║██║██║ ██╔╝██╔══██╗██╔═══██╗██╔══██╗██║   ██║██╔════╝██║  ██║
        ██╔████╔██║██║█████╔╝ ██████╔╝██║   ██║██████╔╝██║   ██║███████╗███████║
        ██║╚██╔╝██║██║██╔═██╗ ██╔══██╗██║   ██║██╔═══╝ ██║   ██║╚════██║██╔══██║
        ██║ ╚═╝ ██║██║██║  ██╗██║  ██║╚██████╔╝██║     ╚██████╔╝███████║██║  ██║
        ╚═╝     ╚═╝╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚═╝      ╚═════╝ ╚══════╝╚═╝  ╚═╝
        '''
    version = "1.0.0"
    github = "https://github.com/ariafatah0711/MikroPush"
    author = "ariafatah0711"

def banner():
    print(app.logo)
    print(f"{bcolors.ORANGE}{app.author} {bcolors.FAIL}github: {app.github} {bcolors.OKGREEN}version:{app.version}")
    print(f"{bcolors.OKGREEN}[✓] input success {bcolors.ENDC} | {bcolors.FAIL}[☓] input failed {bcolors.ENDC} | {bcolors.HEADER}[O] output success {bcolors.ENDC}\n")