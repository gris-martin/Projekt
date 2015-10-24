from pynma import PyNMA
from urllib import request
from time import sleep

# API-nyckel för Notify My Android
p = PyNMA('3a1ff5b87baff90fc5ac7c73e37c3f7d07a4a48e1035b4f2')

old_prods = []
while True:
	# Hämta hemsidan (grafikkort)
	r = request.urlopen("http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/grafikkort/")
	bytecode = r.read()
	strcode = bytecode.decode(encoding = 'ISO-8859-1')

	# Plocka ut alla artiklar och leta efter "Klass 4" och "Klass 5"
	prods = strcode.split('prod_list_row')
	for prod in prods[1:]:
		l = prod.split('href')[1].split('>')[1].split('<')[0]
		price = prod.split('price')[1].split('>')[1].split('<')[0].strip().split('&')[0] + ' kr'
		formatted = l + '\n' + price
		if (formatted not in old_prods):
			if ("Klass 4" in l): 
				print(formatted)
				p.push("Lilldatorn", "Grafikkort - Klass 4", formatted, "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/grafikkort/")
				old_prods.append(formatted)
			elif ("Klass 5" in l):
				p.push("Lilldatorn", "Grafikkort - Klass 5", formatted, "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/grafikkort/")
				old_prods.append(formatted)

	
	# Hämta hemsidan (Chassis)
	r = request.urlopen("http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/chassi/")
	bytecode = r.read()
	strcode = bytecode.decode(encoding = 'ISO-8859-1')

	# Plocka ut alla artiklar och leta efter "Klass 4" och "Klass 5"
	prods = strcode.split('prod_list_row')
	for prod in prods[1:]:
		l = prod.split('href')[1].split('>')[1].split('<')[0]
		price = prod.split('price')[1].split('>')[1].split('<')[0].strip().split('&')[0] + ' kr'
		formatted = l + '\n' + price
		if (formatted not in old_prods):
			if ("Klass 4" in l): 
				print(formatted)
				p.push("Lilldatorn", "Chassi - Klass 4", formatted, "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/chassi/")
				old_prods.append(formatted)
			elif ("Klass 5" in l):
				p.push("Lilldatorn", "Chassi - Klass 5", formatted, "http://www.webhallen.com/se-sv/datorkomponenter/fyndvaror/chassi/")
				old_prods.append(formatted)