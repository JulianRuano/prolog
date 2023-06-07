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
producto(chocoramo,mecato,2000).
producto('galletas festival',mecato,1500).


%Hechos sucursal(nombre,direccion)
sucursal(centro,'calle 7 # 7-1A').
sucursal(norte,'calle 2 # 2-2B').
sucursal(sur,'carrera 4 # 7-3').

%Hechos compra(nombreCliente,[producto1,producto2,...],sucursal)
compra(maria,[camisa,nike,choclitos],centro).
compra(ana,[pantalon,tenis,jumbo],norte).
compra(juan,[camisa,tenis,'jabon rey'],sur).
compra(pedro,[pantalon,nike,escoba],centro).
compra(maria,['jabon rey',tenis,choclitos],norte).
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
        ,ListaPrecios).   %Obtenemos una lista con los precios totales de cada compra

%3) Regla para calcular las ganancias de cada una de las sucursales:
calcularGananciasSucursal(Sucursal, Ganancias) :-     
    findall(PrecioTotal,                                
        (compra(_, Productos, Sucursal), calcularPrecioTotal(Productos, PrecioTotal)),
        ListaPrecios),      %Obtenemos una lista con los precios totales de cada compra
    sum_list(ListaPrecios, Ganancias).    %Sumamos los precios totales de cada compra

calcularGananciasTotales() :- calcularGanancias(Sucursales),imprimir(Sucursales).

calcularGanancias(Sucursales) :- 
        findall(Ganancias,
        (sucursal(Sucursal, _),calcularGananciasSucursal(Sucursal, Ganancias))
        ,Sucursales).     %Obtenemos una lista con las ganancias de cada sucursal


%4) Regla para calcular el precio total de una categoria:
calcularPrecioCategoria(Categoria, PrecioTotal) :-        
    findall(Precio,                        
        (producto(_, Categoria, Precio)),
        ListaPrecios),               %Obtenemos una lista con los precios de cada producto de la categoria
    sum_list(ListaPrecios, PrecioTotal).   %Sumamos los precios de cada producto de la categoria


%5) Que productos prefieren las mujeres:
productoPreferidoMujeres() :-  listaProductoMujeres(ListaProductos),imprimir(ListaProductos).

listaProductoMujeres(ListaProductos) :-  %Calculamos los productos comprados por mujeres
    findall(Producto,                    %Obtenemos una lista con los productos comprados por mujeres
        (compra(Cliente, Productos, _), mujer(Cliente), member(Producto, Productos)),
        ListaProductos).

%6) Determinar si un cliente ha comprado un producto específico
compra_cliente(Cliente, Productos, Sucursal) :-
    compra(Cliente, Productos, Sucursal).  %Es verdadero si el cliente ha comprado los productos en la sucursal

compro(Cliente, Producto) :-
    compra_cliente(Cliente, Productos, _),
    member(Producto, Productos).   %Es verdadero si el producto es miembro de la lista de productos comprados por el cliente

%7) Determinar si un cliente ha comprado al menos un producto de cada categoría
% El forall es verdadero si el predicado es verdadero para todos los valores de la lista forall(Condicion, Predicado)

compro_todas_categorias(Cliente) :-
    forall(categoria(Categoria), (compro(Cliente, Producto), producto(Producto, Categoria, _))).

%8) Obtener una lista de productos comprados en una categoría específica por un cliente
    productos_categoria(Cliente, Categoria, ListaProductos) :-
    findall(Producto, 
    (compro(Cliente, Producto), producto(Producto, Categoria, _)), 
    ListaProductos).     %Obtenemos una lista con los productos comprados por el cliente en la categoria

%9) Calcular el total gastado por un cliente en una categoría específica
total_categoria(Cliente, Categoria, Total) :- 
    productos_categoria(Cliente, Categoria, ListaProductos),
    total_productos(ListaProductos, Total). 

total_productos([], 0).                
total_productos([Producto|Productos], Total) :-
    producto(Producto, _, Precio),
    total_productos(Productos, TotalRestante),
    Total is Precio + TotalRestante.    %Sumamos el precio del producto con el total restante

%10) Calcular el total gastado por un cliente en una sucursal específica
total_sucursal(Cliente, Sucursal, Total) :-  
    compra(Cliente, Productos, Sucursal),
    total_productos(Productos, Total).  %Usamos la regla total_productos del punto 9







