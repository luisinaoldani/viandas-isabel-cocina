# Viandas Isabel Cocina

## Propuesta TP Desarrollo - Lenguaje de Programación Java

## Grupo

### Integrantes

| Legajo | Apellido y Nombre |
|--------|-------------------|
| 53999 | Oldani, Luisina María |
| 55158 | Setti, Francisco |

## Tema

Sistema de venta mayorista de viandas.

### Descripción

La aplicación web cliente-servidor a desarrollar consiste en un sistema de venta mayorista de viandas a distintos tipos de clientes comerciales para la empresa **Isabel Cocina**. Los pedidos podrán ser realizados tanto por empresas que encargan viandas semanales para sus empleados, como por negocios que compran para su posterior reventa. El sistema gestionará la carga de pedidos para facilitar la organización de la producción y su entrega.

## Modelo
[Enlace a Modelo de Dominio](https://drive.google.com/file/d/1iFXt3sFQhubpgW-yk16cap_vWmWM1m17/view?usp=sharing)

## Checklist

### Regularidad

| Requerimiento | Detalle |
| :--- | :--- |
| **ABMC simple** | 1. Pedido<br>2. Vianda |
| **ABMC dependiente** | 1. Ingrediente {depende de} Vianda |
| **CU NO-ABMC** | 1. CUU Realizar pedido |
| **Listado simple** | 1. Listado de las viandas => Detalle muestra información completa de las viandas y sus ingredientes |
| **Listado complejo** | - |

### Aprobación Directa

| Requerimiento | Detalle/Listado de casos incluidos |
| :--- | :--- |
| **ABMC** | 1. Usuario<br>2. Administrador<br>3. Cliente<br>4. Empresa<br>5. Mayorista<br>6. Vianda<br>7. Ingrediente<br>8. Ingrediente_vianda<br>9. Pedido<br>10. Detalle_pedido |
| **CU "Complejo"**(nivel resumen) | 1. CUR Gestionar pedido (CUU Realizar pedido y CUU Preparar pedido) |
| **Listado complejo** | 1. Listado de pedidos confirmados |
| **Nivel de acceso** | 1. Usuario<br>2. Administrador |

