
import Control.Exception
import System.IO.Unsafe
import Data.Maybe
import Data.Char
import GHC.Exts.Heap (Closure)

import Container (Container, newC, destinationC, netC)

import Route (Route, inOrderR, newR)
import Stack (Stack, newS, freeCellsS, stackS, netS, holdsS, popS, ciudadesEnPila)
import Vessel ( Vessel, newV, freeCellsV, loadV, unloadV, netV, ciudadesEnVessel)

-- defino testF para facilitar el test de las funciones 
testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do 
        result <- tryJust isException (evaluate action)
        return $ case result of
            Left _ -> True 
            Right _ -> False 
        where
            isException :: SomeException -> Maybe ()
            isException _ = Just ()


--testRoutes :: [Bool] -- aca esperamos que todos tiren True, de ser ese el caso, estaria inRoderR las funciones de Route bien


-- Chequeo Container.hs:
--     - Que anden bien con info normal (True)
cont1 :: Container
cont1 = newC "city1" 5
--t1 = not (testF cont1)

--     - Que no anden con numeros negativos (True) (Busco error)
contCantErr::Container
contCantErr = newC "city1" (-5) --Change
--t2 = testF contCantErr 

-- Chequeo destinationC & netC:
--     destinationC:
--         - con weight > 0 (True)
--t3 = destinationC cont1 == "city1"

--         - con weight < 0 (True) (Busco error)
--t4 = testF (destinationC contCantErr)


    -- Chequeo netC:
    --     - con weight > 0 (True)
-- t5 = netC cont1 == 5

    --     - con weight < 0 (True) (Busco error)
-- t6 = testF (netC contCantErr)


-- Chequeo Route.hs:
--     - ruta con tres ciudades distintas (True)
ruta1 = newR ["city1", "city2", "city3","city4","city5"] --Change
-- t7 = not (testF ruta1)

--     - ruta vacia {Sin ningun tipo de string} (True) (Busco error)
rutaFail1 = newR []
-- t8 = testF (rutaFail1)

-- Chequeo funcion inOrder:
--     - Ambos strings dos ciudades en el orden correcto de la Route (True)
--t9 = inOrderR ruta1 "city1" "city2"

--     - Orden incorrecto de los strings sobre la misma lista (not False)
--t10 = not (inOrderR ruta1 "city2" "city1")

--     - Sobre lista correcta, string 1 no pertenece (not False)
-- t11 = not (inOrderR ruta1 "XXXX" "city3")

--     - Sobre lista correcta, string 2 no pertenece (not False)
-- t12 = not (inOrderR ruta1 "city3" "XXXX")


--     - Ingreso ambos incorrectos (not False)
-- t13 = not (inOrderR ruta1 "XXSS" "XXXX")


--     - Ingreso ambos elementos iguales y correctos (True)
-- t14 = inOrderR ruta1 "city3" "city3"

--     - En lista de elemento unico ambos pertenecen (True)
rutaOrd1 = newR ["cityO"]
-- t15 = inOrderR rutaOrd1 "cityO" "cityO"

--     - En lista de elemento unico str1 no pertenece (not False)
-- t16 = not (inOrderR rutaOrd1 "XXXX" "cityO")

--     - En lista de elemento unico str2 no pertenece (not False)
-- t17 = not (inOrderR rutaOrd1 "cityO" "XXXX")

-- Chequeo Stack.hs:
--     - Generacion stack de cantidad > 0 (True) -- Change
stack1 = newS 5
-- t18 = not (testF stack1)

--     - Generacion stack cantidad < 0 (True) (Busco error)
stackErr1 = newS (-5)
-- t19 = testF stackErr1

--     - Generacion stack cantidad = 0 (True) (Busco error)
stackErr2 = newS 0
-- t20 = testF stackErr2


-- Chequeo freeCellsS:
--     - stack vacio (True)
-- t21 = freeCellsS stack1 == 5

--     - Menos containers que la cantidad limite (True)
cont5 = newC "city5" 2
cont4 = newC "city4" 3
cont3 = newC "city3" 4
cont2 = newC "city2" 1

stack2 = stackS stack1 cont5
-- t22 = freeCellsS stack2 == 4

--     - Igual de containers que la cantidad limite (True)
stack3 = stackS stack2 cont4
stack4 = stackS stack3 cont3
stack5 = stackS stack4 cont2
stack6 = stackS stack5 cont1

-- t23 = freeCellsS stack6 == 0

--     - Mas containers que la cantidad limite {nunca se llegara a esta situacion}

-- Chequeo stackS:
--     - Apilo un container sobre stack vacio (True)
-- t24 = not (testF (stackS stack1 cont1))

--     - Apilo container sobre stack con capacidad (True)
-- t25 = not (testF (stackS stack4 cont2))

--     - Apilo container sobre stack sin capacidad (True) (Busco error)
-- t26 = testF(stackS stack6 cont1)

-- Chequeo netS:
--     - Menos containers que la cantidad limite (True)
-- t27 = netS stack5 == 10

--     - Igual de containers que la cantidad limite (True)
-- t28 = netS stack6 == 15

--     - Mas containers que la cantidad limite (True) {Nunca se llega a aquel caso}

-- Chequeo holdsS:
contNotCity = newC "XXXX" 5

--     Sobre stack vacio:

--     - Verifico valor con container perteneciente a ruta sobre stack vacio (True)
-- t29 = holdsS stack1 cont2 ruta1

--     - Verifico valor con container no perteneciente a ruta sobre stack vacio (not False)
-- t30 = not (holdsS stack1 contNotCity ruta1)

--     ----
--     Sobre stack con capacidad:

--     - Verifico valor con container perteneciente a ruta sobre stack cargado con ciudad que viene Luego (True)
-- t31 = holdsS stack5 cont1 ruta1

--     - Verifico valor con container no perteneciente a ruta (not False)
-- t32 = not (holdsS stack5 contNotCity ruta1)
    
--     - Verifico valor con container perteneciente a ruta sobre stack con ciudad que viene Antes (not False)
-- t33 = not (holdsS stack5 cont5 ruta1)

--     - Verifico container perteneciente, ciudad Luego PERO container con sobre peso (not False)
contNotWeight = newC "city1" 88
-- t34 = not (holdsS stack5 contNotWeight ruta1)

--     - Verifico container peso bajo pero el stack esta con sobre peso (not False)
cont7 = newC "city4" 15
stack7 = stackS stack1 cont7
stack8 = stackS stack7 cont7
-- t35 = not (holdsS stack8 cont1 ruta1)

--     ----
--     Stack sin capacidad:

--     - Container perteneciente con Stack sin cantidad disponible (not False)
-- t36 = not (holdsS stack6 cont1 ruta1)

--     - Container no perteneciente con Stack sin cantidad disponible (not False)
-- t37 = not (holdsS stack6 contNotCity ruta1)

-- Chequeo de popS:
--     - Prueba de popS sobre un stack vacio y ciudad correcta (True) (No busco error, es responabilidad de unloadV)
-- t38 = not (testF(popS stack1 "city2"))

--     - Prueba de popS sobre un stack vacio y ciudad incorrecta (True) (No busco error, es responabilidad de unloadV)
-- t39 = not (testF(popS stack1 "XXXX"))

--     - Uso sobre un stack con 1 container a sacar y ciudad correcta (True)
stackPop1 = popS stack5 "city2"
-- t40 = ciudadesEnPila stackPop1 == ["city3", "city4", "city5"]


--     - Uso sobre un stack con mas de 1 container a sacar y ciudad correcta (True)
stackPop2 = stackS stack5 cont2
stackPop3 = popS stackPop2 "city2"
-- t41 = ciudadesEnPila stackPop3 == ["city3", "city4", "city5"]

--     - Uso sobre un stack ya cargado pero string incorrecto (True) (No hay cambio sobre stack)
stackPop4 = popS stackPop2 "XXXX"
-- t42 = ciudadesEnPila stackPop4 == ciudadesEnPila stackPop2

 
-- Chequeo Vessel.hs:

--     - Creacion Vessel con ruta con contenido y #bahias, altura > 0 (True)
-- t43 = not (testF (newV 4 4 ruta1))

--     - Creacion Vessel con ruta VACIA, #bahias, altura > 0 (True) (Busco error)
vesselFail1 = newV 4 4 rutaFail1
-- t44 = testF(vesselFail2) || testF (rutaFail1)

--     - Creacion Vessel con ruta con contenido y #bahias > 0 ,  altura = 0 (True) (Busco error)
vesselFail2  = newV 4 0 ruta1
-- t45 = testF (vesselFail2)

--     - Creacion Vessel con ruta con contenido y #bahias = 0 , altura > 0 (True) (Busco error)
vesselFail3  = newV 0 4 ruta1
-- t46 = testF (vesselFail3)


-- Verificacion loadV:
--     - Cargado de container sobre buque vacio (True)
vessel1 = newV 2 4 ruta1
vessel2 = loadV vessel1 cont5
-- t47 = netV vessel1 /= netV vessel2

--     - Cargado de container sobre buque con contenedores varios pero orden correcto (True)
vessel3 = loadV vessel2 cont4
-- t48 = ciudadesEnVessel vessel3 == [["city4","city5"],[]]

--     - Cargado de container sobre buque con contenedores varios pero orden INCORRECTO (True) (Busco error)
vessel4 = loadV vessel3 cont5
vessel5 = loadV vessel4 cont3
vessel6 = loadV vessel5 cont4
vessel7 = loadV vessel6 cont5

-- t49 = testF(freeCellsV vessel7)
    
--     - Cargado de peso justo (True)
vesselWeight = newV 2 2 ruta1
contW5 = newC "city5" 15
contW1 = newC "city2" 8
contW3 = newC "city1" 10
contW4 = newC "city1" 1
vesselW1 = loadV vesselWeight contW5
vesselW2 = loadV vesselW1 contW5
vesselW3 = loadV vesselW2 contW4
vesselW4 = loadV vesselW3 contW4

-- t50 = netV vesselW2 == 30

--     - Cargado de cantidad justa (True)
-- t51 = freeCellsV vesselW4 == 0

--     - Intento de sobre-llenado de buque por sobre-peso (True) (Busco error)
vesselW5 = loadV vesselW2 contW1
-- t52 = testF (freeCellsV vesselW5)

--     - Intento de sobre-llenado de buque en terminos de cantidad (True) (Busco error)
vesselW6 = loadV vesselW4 contW4
-- t53 =  testF (unloadV vesselW6 "city1")

-- Verificacion de freeCellsV:
--     - Celdas libres en un vessel vacio = cantidad * altura (True)
-- t54 = freeCellsV vesselWeight == 4

--     - Celdas libres en vessel mas cargado (True)
-- t55 = freeCellsV vesselW3 == 1

-- Chequeo netV:
--     - Chequeo de peso de Buque vacio (True)
-- t56 = netV vesselWeight == 0

--     - Chequeo de peso de buque mas lleno (True)
-- t57 = netV vesselW3 == 31

--     - Peso para un buque en el limite (True)
contNetV = newC "city1" 5
vesselWX2 = loadV vesselW2 contNetV
vesselWX3 = loadV vesselWX2 contNetV
-- t58 = netV vesselWX3 == 40


-- Chequeo unloadV:
--     - Pruebo vaciar un buque cargado sobre una ciudad correcta (True)
vesselULV = unloadV vesselW3 "city1"
-- t59 = ciudadesEnVessel vesselULV == [["city5"],["city5"]]

--     - Pruebo vaciar un buque en una ciudad no perteneciente a la ruta (True) Busco error
-- t60 = testF (unloadV vesselW3 "XXXX")

--     - Pruebo vaciar un buque en una ciudad de orden incorrecto (True) (No hay cambio alguno)
vesselULV2 = unloadV vesselW4 "city5"
-- t61 = ciudadesEnVessel vesselULV2 == ciudadesEnVessel vesselW4

--     - Pruebo vaciar un buque vacio (True) (Busco error)
-- t62 = testF (unloadV vessel1 "city1")

t = [
    not (testF cont1), --Que anden bien con info normal (True)
    testF contCantErr, -- Que no anden con numeros negativos (True) (Busco error)
    destinationC cont1 == "city1", -- Destination con weight>0 (True)
    testF (destinationC contCantErr), --con weight < 0 (True) (Busco error)
    netC cont1 == 5,--con weight > 0 (True)
    testF (netC contCantErr), --con weight < 0 (True) (Busco error)
    not (testF ruta1),--ruta con tres ciudades distintas (True)
    testF (rutaFail1), --ruta vacia {Sin ningun tipo de string} (True) (Busco error)
    inOrderR ruta1 "city1" "city2", --Ambos strings dos ciudades en el orden correcto de la Route (True)
    not (inOrderR ruta1 "city2" "city1"), --Orden incorrecto de los strings sobre la misma lista (not False)
    not (inOrderR ruta1 "XXXX" "city3"), --Sobre lista correcta, string 1 no pertenece (not False)
    not (inOrderR ruta1 "city3" "XXXX"),  --Sobre lista correcta, string 2 no pertenece (not False) (t12)
    not (inOrderR ruta1 "XXSS" "XXXX"),--Ingreso ambos incorrectos (not False)
    inOrderR ruta1 "city3" "city3", --Ingreso ambos elementos iguales y correctos (True)
    inOrderR rutaOrd1 "cityO" "cityO",--En lista de elemento unico ambos pertenecen (True)
    not (inOrderR rutaOrd1 "XXXX" "cityO"), --En lista de elemento unico str1 no pertenece (not False)
    not (inOrderR rutaOrd1 "cityO" "XXXX"), --En lista de elemento unico str2 no pertenece (not False)
    not (testF stack1), --Generacion stack de cantidad > 0 (True)
    testF stack1, -- Generacion stack cantidad < 0 (True) (Busco error)
    testF stackErr2, --Generacion stack cantidad = 0 (True) (Busco error)
    freeCellsS stack1 == 5,--stack vacio (True)
    freeCellsS stack2 == 4, --Menos containers que la cantidad limite (True)
    freeCellsS stack6 == 0,--Igual de containers que la cantidad limite (True)
    not (testF (stackS stack1 cont1)), --Apilo un container sobre stack vacio (True)
    not (testF (stackS stack4 cont2)), --Apilo container sobre stack con capacidad (True)
    testF(stackS stack6 cont1), --Apilo container sobre stack sin capacidad (True) (Busco error)
    netS stack5 == 10,--Menos containers que la cantidad limite (True)
    netS stack6 == 15, --Igual de containers que la cantidad limite (True)
    holdsS stack1 cont2 ruta1,--Verifico valor con container perteneciente a ruta sobre stack vacio (True)
    not (holdsS stack1 contNotCity ruta1), --Verifico valor con container no perteneciente a ruta sobre stack vacio (not False)
    holdsS stack5 cont1 ruta1, --Verifico valor con container perteneciente a ruta sobre stack cargado con ciudad que viene Luego (True)
    not (holdsS stack5 contNotCity ruta1), --Verifico valor con container no perteneciente a ruta (not False) (t32)
    not (holdsS stack5 cont5 ruta1), --Verifico valor con container perteneciente a ruta sobre stack con ciudad que viene Antes (not False)
    not (holdsS stack5 contNotWeight ruta1), --Verifico container perteneciente, ciudad Luego PERO container con sobre peso (not False)
    not (holdsS stack8 cont1 ruta1), --Verifico container peso bajo pero el stack esta con sobre peso (not False)
    not (holdsS stack6 cont1 ruta1), --Container perteneciente con Stack sin cantidad disponible (not False)
    not (testF(popS stack1 "city2")), --Prueba de popS sobre un stack vacio y ciudad correcta (True) (No busco error, es responabilidad de unloadV)
    not (testF(popS stack1 "XXXX")),--Prueba de popS sobre un stack vacio y ciudad incorrecta (True) (No busco error, es responabilidad de unloadV)
    ciudadesEnPila stackPop1 == ["city3", "city4", "city5"], --Uso sobre un stack con 1 container a sacar y ciudad correcta (True)
    ciudadesEnPila stackPop3 == ["city3", "city4", "city5"], --Uso sobre un stack con mas de 1 container a sacar y ciudad correcta (True)
    ciudadesEnPila stackPop4 == ciudadesEnPila stackPop2, --Uso sobre un stack ya cargado pero string incorrecto (True) (No hay cambio sobre stack)
    not (testF (newV 4 4 ruta1)), --Creacion Vessel con ruta con contenido y #bahias, altura > 0 (True)
    testF(vesselFail2) || testF (rutaFail1), --Creacion Vessel con ruta VACIA, #bahias, altura > 0 (True) (Busco error)
    testF (vesselFail2), --Creacion Vessel con ruta con contenido y #bahias > 0 ,  altura = 0 (True) (Busco error)
    testF (vesselFail3), --Creacion Vessel con ruta con contenido y #bahias = 0 , altura > 0 (True) (Busco error)
    netV vessel1 /= netV vessel2, --Cargado de container sobre buque vacio (True)
    ciudadesEnVessel vessel3 == [["city4","city5"],[]], --Cargado de container sobre buque con contenedores varios pero orden correcto (True)
    testF(freeCellsV vessel7), --Cargado de container sobre buque con contenedores varios pero orden INCORRECTO (True) (Busco error)
    netV vesselW2 == 30, --Cargado de peso justo (True)
    freeCellsV vesselW4 == 0, --Cargado de cantidad justa (True)
    testF (freeCellsV vesselW5), --Intento de sobre-llenado de buque por sobre-peso (True) (Busco error)
    testF (unloadV vesselW6 "city1"), --Intento de sobre-llenado de buque en terminos de cantidad (True) (Busco error)
    freeCellsV vesselWeight == 4, --Celdas libres en un vessel vacio = cantidad * altura (True)
    netV vesselWeight == 0, --Chequeo de peso de Buque vacio (True)
    netV vesselW3 == 31, --Chequeo de peso de buque mas lleno (True)
    netV vesselWX3 == 40, --Peso para un buque en el limite (True)
    ciudadesEnVessel vesselULV == [["city5"],["city5"]], --Pruebo vaciar un buque cargado sobre una ciudad correcta (True)
    testF (unloadV vesselW3 "XXXX"), --Pruebo vaciar un buque en una ciudad no perteneciente a la ruta (True) Busco error
    ciudadesEnVessel vesselULV2 == ciudadesEnVessel vesselW4, --Pruebo vaciar un buque en una ciudad de orden incorrecto (True) (No hay cambio alguno)
    testF (unloadV vessel1 "city1"), --Pruebo vaciar un buque vacio (True) (Busco error)

    True
    ]










