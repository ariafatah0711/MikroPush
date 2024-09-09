import argparse
from pathlib import Path
from util.bcolors import bcolors

class desc:
    i = "Alamat IP dari MikroTik yang akan diakses (contoh: 192.168.88.1)"
    p = "Port SSH untuk koneksi (default: 22)"
    u = "Username untuk login ke MikroTik"
    P = "Password untuk login ke MikroTik"
    v = "Aktifkan mode verbose: -v (tampilkan perintah), -vv (tampilkan perintah dan hasil perintah)"
    c = "Perintah sederhana (contoh: 'ip add print"
    s = "File script yang berisi perintah untuk dieksekusi"
    f = "Folder yang berisi beberapa script untuk dieksekusi"
    info = "Tampilkan informasi sistem MikroTik (location: scripts/info.sc)"
    remove = "Hapus konfigurasi dari MikroTik (location: scripts/remove.sc)"
    
    epilog = f'''
Contoh Penggunaan:
  {bcolors.ORANGE}python3 mikropush.py -i 192.168.88.1 -u admin -c "file print"
  python3 mikropush.py -i 192.168.88.1 -P 2020 -u admin -s script.sc -v
  python3 mikropush.py -i 192.168.88.1 -P 2020 -u admin --remove --info -s script.sc
  python3 mikropush.py -i 192.168.88.1 -u admin -p admin -f zoom
{bcolors.ENDC}
script penggunaan:
  {bcolors.ORANGE}comment script: "# ", "; "
  file script   : *.sc*
'''

def getArgument():
    parser = argparse.ArgumentParser(
        formatter_class=argparse.RawDescriptionHelpFormatter,
        description=f'{bcolors.HEADER}Aplikasi Network Automation untuk MikroTik{bcolors.ENDC}',
        epilog=desc.epilog
    )

    # Argumen wajib
    parser.add_argument("-i", metavar="IP", type=str, required=True, help=desc.i)
    parser.add_argument("-P", metavar="Port", type=str, required=False, default=22, help=desc.p)
    parser.add_argument("-u", metavar="Username", type=str, required=True, help=desc.u)
    
    # Argumen opsional
    parser.add_argument("-p", metavar="Password", type=str, required=False, default="", help=desc.P)
    parser.add_argument("-v", "--verbose", action="count", default=0, help=desc.v)

    # Pilihan perintah
    parser.add_argument("-c", metavar="Command", type=str, required=False, help=desc.c)
    parser.add_argument("-s", metavar="Script", type=Path, required=False, help=desc.s)
    parser.add_argument("-f", "-F", metavar="Folder", type=Path, required=False, help=desc.f)

    # Opsi tambahan
    parser.add_argument("-I", "--info", action="store_true", help=desc.info)
    parser.add_argument("-R", "--remove", action="store_true", help=desc.remove)

    return parser.parse_args()
