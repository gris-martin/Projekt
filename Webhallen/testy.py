import time
from copy import copy
from pynma import PyNMA
from urllib import request
from sys import stdout

# API-nyckel for Notify My Android
p = PyNMA('3a1ff5b87baff90fc5ac7c73e37c3f7d07a4a48e1035b4f2')

# Main function
def main():
        old = []
        url_g = "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/grafikkort/"
        klass_g = [3, 4, 5]
        namn_g = "Grafikkort"

        url_c = "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/chassi/"
        klass_c = [3, 4, 5]
        namn_c = "Chassi"

        # url_m = 'http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/minnen/dimm/'
        # klass_m = [3, 4, 5]
        # namn_m = 'Minne'
        # opt_m = 'DDR3'

        sleep_time = 60
        while True:
                start = time.time()
                check_url(url_g, klass_g, namn_g, old)
                check_url(url_c, klass_c, namn_c, old)
                # check_url(url_m, klass_m, namn_m, old)

                stdout.flush()
                while (time.time() - start) < sleep_time:
                        time.sleep(1)
        

#Kollar URLen efter forandringar och skickar till Notify My Android
def check_url(url, klass, namn, old_prods, opt = []):
        # Hamta hemsidan (grafikkort)
        r = request.urlopen(url)
        bytecode = r.read()
        strcode = bytecode.decode(encoding = 'ISO-8859-1')
        
	# Plocka ut alla artiklar och leta efter klasser
        prods = strcode.split('prod_list_row')
        for prod in prods[1:]:
                l = prod.split('href')[1].split('>')[1].split('<')[0]
                price = prod.split('price')[1].split('>')[1].split('<')[0].strip().split('&')[0] + ' kr'
                formatted = l + '\n' + price
                if (formatted not in old_prods):
                        print(formatted+'\n')
                        for k in klass:
                                search_list = copy(opt)
                                klass_str = 'Klass ' + str(k)
                                try:
                                        search_list.append(klass_str)
                                except AttributeError:
                                        search_list = [search_list, klass_str]
                                if not any(x not in l for x in search_list):
                                        to_phone(namn + ' - ' + klass_str, formatted, url, old_prods)

                                                                        
        return


def to_phone(title, message, url, old_prods, sender = "Lilldatorn"):
        # print(time.strftime("%x %X ") + message + "\n")
        p.push(sender,
               title,
               message,
               url)
        old_prods.append(message)
        return

main()
