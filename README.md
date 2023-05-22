# IngSoft_Appmoviles_CSHS

## Proceso de generación de APK en IDE Android Studio (versión Flamingo | 2022.2.1)

En el IDE Android Studio, una vez esté culminado el proceso de desarrollo de funcionalidades y desarrollo de pruebas, y cuando todos los cambios hayan sido subidos al repositorio conservando la estructura de ramas descrita en la Wiki del proyecto, se puede proceder a generar el APK para ser instalado en los dispositivos de pruebas.

Para este proceso, basta con ir al menú Build, luego a la opción Build Bundle(s) / APK(s) y seleccionar Build APK(s). Luego, el proceso empezará a construir el APK sin firmar, y al final, será guardado en la carpeta /app/build/outputs/apk/androidTest/debug del repositorio del proyecto. 

Una vez se obtenga el archivo APK, es posible instalarlo en el dispositivo.

## Proceso de instalación de APK en dispositivo móvil Android

Como primera medida, se debe garantizar que el dispositivo móvil está listo para instalar archivos no descargados del Play Store, para eso se debe habilitar la depuración USB en el dispositivo Android, ya que para instalar y probar la aplicación en el dispositivo Android, se necesita habilitar la opción de depuración USB en el dispositivo. Para esto, se debe seguir estos pasos:

- Ir a Ajustes > Acerca del teléfono.
- Buscar la opción Número de compilación y tocar en ella 7 veces para habilitar las opciones de desarrollo.
- Regresar a la pantalla de ajustes y buscar Opciones de desarrollo.
- Activar la opción Depuración USB.

Una vez sea configurado el proceso anterior, se pasa por cable el .apk al telefono y se instala. El proceso debe terminar con la instalación exitosa. 

## Abrir y navegar por la aplicación

Una vez sea instalada la aplicación, se da clic en el ícono de la aplicación para abrirla (depende de cada dispositivo si este ícono aparece en el menú de aplicaciones o en la pantalla de inicio) y es posible navegar por la aplicación. 
