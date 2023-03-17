SELECT t.nombre_tienda 'Tienda', v.mes_venta 'Mes', p.nombre_producto 'Producto', v.cantidad_vendida 'Cantidad vendida', (v.cantidad_vendida * v.precio_unitario) 'Total Ventas'
FROM VENTAS v
INNER JOIN TIENDAS t
INNER JOIN PRODUCTOS p 
ON v.id_tienda = t.id_tienda and v.id_producto = p.id_producto
ORDER BY v.id_tienda, v.mes_venta, v.id_producto;