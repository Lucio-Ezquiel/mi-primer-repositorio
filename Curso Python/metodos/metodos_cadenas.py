cadena1 = "hola como estas"
cadena2 = "buenas tardes"

#Estructura de los metodos DATO.METODO Y ()

#Convierte a mayuscula
mayusc = cadena1.upper()

#Convierte a minuscula
minusc = cadena1.lower()

#Primera letra en mayuscula
primer_letra_mayuscula = cadena1.capitalize()

#Buscamos cadena en otra cadena, si no hay coincidencias devuelve -1
busqueda_find = cadena1.find("hola")

#Buscamos una cadena en otra cadena, si no hay coincidencias devuelve error
busqueda_index = cadena1.index("hola")

#Si es numerico devuelve true, sino false
es_numerico = cadena1.isnumeric()

#Si es alfanumerico devuelve true, sino false
es_alfanumerico = cadena1.isalpha()

#Devuelve cuantas veces coincide una cadena dentro de otra cadena
contar_coincidencias = cadena1.count("h")

#Cuenta cuantos caracteres tiene una cadena
contar_caracteres = len(cadena1)

#Verificamos si una cadena empieza con otra cadena dada, si es asi devuelve true
empieza_con = cadena1.startswith("h")

#Verificamos si una cadena termina con otra cadena dada, si es asi devuelve true
termina_con = cadena1.endswith("s")

#Reemplaza un valor por otro, primero lo que quiero reemplazar y luego por lo que se reemplaza
cadena_nueva = cadena1.replace("la","lu")

#Separar cadenas con la cadena que le pasemos
cadena_separada = cadena1.split(",")

print(cadena_separada)