# App OpenGL ES 1.0 - 2.0

**Esta aplicación, desarrollada en Android Studio, presenta una colección de renders que se han creado mediante la API OpenGL ES. El proyecto abarca la programación en el lenguaje Java para definir diversas geometrías y aprovechar todas las funcionalidades proporcionadas por la API. Además, integramos GLSL (OpenGL Shading Language) para la creación de shaders, los cuales están optimizados para su uso exclusivo con la versión 2.0 de OpenGL ES. A lo largo de esta documentación, exploraremos la implementación y personalización de esta aplicación para crear experiencias visuales en dispositivos móviles.**


<div align="center">
  
## Portfolio OpenGL ES 1.0 - 2.0

<img width="65%" src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/planetasHorizontal.gif">

</div>


# Índice
- [App OpenGL ES 1.0 - 2.0](#app-opengl-es-10---20)
- [Índice](#índice)
  - [Requisitos](#requisitos)
  - [Instalación](#instalación)
  - [Características](#características)
  - [Uso](#uso)
  - [Screenshots](#screenshots)
  - [Licencia](#licencia)



## Requisitos
El IDE recomendado para trabajar con OpenGL ES es Android Studio, ya que cuenta con las herramientas necesarias para trabajar con la API, incluye Gradle para manejar dependencias y el SDK de Android para compilar y ejecutar la aplicación en un dispositivo o emulador de dispositivos incluido.

En Android Studio, podemos trabajar con Java o con Kotlin.

Es necesario tener los drivers DLL.

- [Android Studio](https://developer.android.com/studio)
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)
- [opengl32.dll](https://www.dll-files.com/download/1a4d16f0b8f0a6d3eae5bdf8c9e2a9f1/opengl32.zip.html)

![openglDLL img](https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/openglDLL.png)

**Código para registrar OpenGL**

![openglDLL img](https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/openglDLLregister.png)



## Instalación
Para abrir el proyecto en Android Studio, utiliza el menú "File" -> "Open" y selecciona la carpeta raíz del proyecto (descomprime el archivo .zip).

## Características

- **API OpenGL ES 1.0:**

Buffers, Blending, Mapeo de texturas, Materiales, Luces, MipMaps, OBJ loader...

- **API OpenGL ES 2.0:**

Shader (Vertex, Fragment), Buffers, Blending, Mapeo de texturas...

## Uso
Pasos para crear un renderizador en OpenGL ES:

<table style="width: 100%;">
  <tr>
    <th>GEOMETRÍA</th>
    <th></th>
  </tr>
  <tr>
    <td>
      <p>Para realizar un renderizado en pantalla utilizando OpenGL ES, es esencial comenzar por definir la geometría deseada.</p>
      <p>Este proceso puede involucrar la creación de múltiples clases distintas que representen la geometría de diversos objetos, todos los cuales podrán ser renderizados en una misma escena.</p>
      <p>Además, en esta etapa del código, se requiere la implementación de los shaders GLSL, especialmente cuando se trabaja con la versión 2.0 de OpenGL ES. Estos shaders desempeñan un papel crucial en la configuración de cómo se representarán y se verán los objetos en la pantalla.</p>
    </td>
    <td  width="500">
      <img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/geometria.jpg">
    </td>
  </tr>
</table>

<table style="width: 100%;">
  <tr>
    <th>RENDER</th>
    <th></th>
  </tr>
  <tr>
    <td>
      <p>La clase encargada de la renderización en OpenGL ES extiende de la clase "Renderer". En esta clase, hacemos uso de todas las geometrías previamente definidas, configuramos cómo se representarán en la escena y llevamos a cabo transformaciones esenciales, tales como escalado, rotación y traslación.</p>
      <p>Es importante señalar que en la versión 2.0 de OpenGL ES, no disponemos de los métodos directos para las transformaciones. En su lugar, debemos crear nuestros propios métodos personalizados para llevar a cabo estas operaciones de transformación.</p>
    </td>
    <td  width="500">
      <img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/render.jpg">
    </td>
  </tr>
</table>

<table style="width: 100%;">
  <tr>
    <th>ACTIVITY Y MANIFEST</th>
    <th></th>
  </tr>
  <tr>
    <td>
      <p>Activity</p>
      <p>Para utilizar las clases de renderización, es necesario crear una actividad de Android. En el método view.setRenderer(new TuClaseDeRenderizado()), definimos el renderizador que se encargará de mostrar esta actividad.</p>
      <p>Dentro del proyecto, puedes emplear la clase TestActivity.java para probar cada renderizador de manera individual.</p>
      <p>Es crucial asegurarse de que en el método view.setEGLContextClientVersion() se especifique la misma versión que se utilizó en las clases de geometría y renderizado.</p>
      <br><br>
      <p>AndroidManifest.xml</p>
      <p>En el archivo app/manifests/AndroidManifest.xml, asegúrate de agregar la etiqueta correspondiente para la actividad que estás utilizando y marcarla como actividad principal.</p>
    </td>
    <td  width="500">
      <img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/activity.jpg">
      <img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/manifest.jpg">
    </td>
  </tr>
</table>

### Load .obj models

Para cargar archivos .obj, es necesario que al exportarlos desde el software de modelado 3D, se especifique que se exportarán como triangulados.

Los archivos .obj deber ser guardados en la carpeta "assets" del proyecto.

Para cargar la geometría, proporciona el nombre del archivo .obj como parámetro en el constructor de la clase ObjModel, como se muestra a continuación:
  
```java
ObjModel monkey = new ObjModel("monaBlender.obj", MisColores.random(967), this.context);
```


## Screenshots

<table style="width:100%">
  <tr>
    <td><img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/intro.gif" ></td>
    <td><img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/menu.jpg" ></td>
    <td><img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/menu10.jpg" ></td>
  </tr>
</table>


**Renderers**

<table style="width:100%">
<tr>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/rubik540.jpg">
</td>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/esfera540.jpg">
</td>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/icosfera540.jpg">
</td>
</tr>
<tr>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/blending540x800.jpg">
</td>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/spotlight540x800.jpg">
</td>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/linterna540x800.jpg">
</td>
</table>

**.obj Loader**

<table style="width:100%">
<td width="50%">
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/monkeyBlender540.jpg">
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/monkey540.jpg">
</td>
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/blenderObjets540x840.jpg">
</td>
</table>

**OpenGL 2.0**

<table style="width:100%">
<td>
<img src="https://github.com/kevoaac/assets/blob/main/img/AppOpenGLes/SistemaSolar1200x740.jpg">
</td>
</table>

## Licencia
Este código está disponible bajo la [Licencia MIT](https://opensource.org/licenses/MIT), lo que significa que es completamente libre y puedes utilizarlo, modificarlo, distribuirlo y utilizarlo sin restricciones.

