frutas = ["banana","manzana","pera","durazno","kiwi"]
cadena = "hola como estas"
numeros = [2,3,4,5,6,7]

for fruta in frutas:
    #Como saltear un valor
    if fruta == "manzana":
        continue
    print(fruta)

for fruta in frutas:
    print(fruta)
    #Para terminar el bucle
    if fruta == "manzana":
        break
else:
    print("Bucle terminado")

for cad in cadena:
    print(cad)

numeros_duplicados = [x*2 for x in numeros]
print(numeros_duplicados)