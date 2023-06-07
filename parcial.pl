%Hechos cliente
mujer(maria).
mujer(ana).
hombre(juan).
hombre(pedro).

%Hechos categoria
categoria(ropa).
categoria(calzado).
categoria(mecato).
categoria(aseo).

%Hechos producto(nombre,categoria,precio)
producto(camisa,ropa,20000).
producto(pantalon,ropa,30000).
producto(nike,calzado,50000).
producto(tenis,calzado,60000).
producto(choclitos,mecato,6500).
producto(jumbo,mecato,1500).
producto('jabon rey',aseo,3000).
producto(escoba,aseo,4000).


%Hechos sucursal(nombre,direccion)
sucursal(centro,'calle 7 # 7-1A').
sucursal(norte,'calle 2 # 2-2B').
sucursal(sur,'carrera 4 # 7-3').

%Hechos compra(nombreCliente,[producto1,producto2,...],sucursal)
compra(maria,[camisa,nike,choclitos],centro).
compra(ana,[pantalon,tenis,jumbo],norte).
compra(juan,[camisa,tenis,'jabon rey'],sur).
compra(pedro,[pantalon,nike,escoba],centro).
compra(maria,[jumbo,tenis,choclitos],norte).
compra(ana,[pantalon,nike,jumbo],sur).

%Reglas 
imprimir([]):-!.
imprimir([X|Y]):- write(X),nl,imprimir(Y).
  

%1) Regla para calcular el precio total de una lista de productos:
calcularPrecioTotal([], 0).
calcularPrecioTotal([Producto|Productos], PrecioTotal) :-
    producto(Producto, _, Precio),
    calcularPrecioTotal(Productos, PrecioRestante),
    PrecioTotal is Precio + PrecioRestante.

%2) Regla para calcular el precio total de una compra:
calcularPrecioCompra(Cliente) :- calcularPrecio(Cliente,ListaPrecios),imprimir(ListaPrecios).

calcularPrecio(Cliente, ListaPrecios) :- 
        findall(PrecioTotal,
        (compra(Cliente, Productos, _),calcularPrecioTotal(Productos, PrecioTotal))
        ,ListaPrecios).

%3) Regla para calcular las ganancias de cada una de las sucursales:
calcularGananciasSucursal(Sucursal, Ganancias) :-                    %Calculamos las ganancias de una sucursal
    findall(PrecioTotal,                                             %Obtenemos una lista con los precios totales de cada compra
        (compra(_, Productos, Sucursal), calcularPrecioTotal(Productos, PrecioTotal)),
        ListaPrecios),
    sum_list(ListaPrecios, Ganancias).                               %Sumamos los precios totales de cada compra

calcularGananciasTotales() :- calcularGanancias(Sucursales),imprimir(Sucursales).

calcularGanancias(Sucursales) :- 
        findall(Ganancias,
        (sucursal(Sucursal, _),calcularGananciasSucursal(Sucursal, Ganancias))
        ,Sucursales).                                               %Obtenemos una lista con las ganancias de cada sucursal


%4) Regla para calcular el precio total de una categoria:
calcularPrecioCategoria(Categoria, PrecioTotal) :-                   %Calculamos el precio total de una categoria
    findall(Precio,                                                  %Obtenemos una lista con los precios de cada producto de la categoria
        (producto(_, Categoria, Precio)),
        ListaPrecios),
    sum_list(ListaPrecios, PrecioTotal).                             %Sumamos los precios de cada producto de la categoria


%5) Que productos prefieren las mujeres:
productoPreferidoMujeres() :-  listaProductoMujeres(ListaProductos),imprimir(ListaProductos).

listaProductoMujeres(ListaProductos) :-                              %Calculamos los productos comprados por mujeres
    findall(Producto,                                                %Obtenemos una lista con los productos comprados por mujeres
        (compra(Cliente, Productos, _), mujer(Cliente), member(Producto, Productos)),
        ListaProductos).
   

   






    

















