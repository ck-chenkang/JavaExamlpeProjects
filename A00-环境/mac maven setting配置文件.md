参考链接：

[mac brew把maven安装到哪里了](https://www.jianshu.com/p/230e0b5de44e)

在下面那个目录下：

```
/opt/homebrew/Cellar/maven/3.8.2/libexec/conf
```



```
.
├── INSTALL_RECEIPT.json
├── LICENSE
├── NOTICE
├── README.txt
├── bin
│   ├── mvn
│   ├── mvnDebug
│   └── mvnyjp
└── libexec
    ├── bin
    │   ├── m2.conf
    │   ├── mvn
    │   ├── mvnDebug
    │   └── mvnyjp
    ├── boot
    │   ├── plexus-classworlds-2.6.0.jar
    │   └── plexus-classworlds.license
    ├── conf
    │   ├── logging
    │   │   └── simplelogger.properties
    │   ├── settings.xml
    │   └── toolchains.xml
    └── lib
        ├── cdi-api-1.0.jar
        ├── cdi-api.license
        ├── commons-cli-1.4.jar
        ├── commons-cli.license
        ├── commons-io-2.6.jar
        ├── commons-io.license
        ├── commons-lang3-3.8.1.jar
        ├── commons-lang3.license
        ├── ext
        │   └── README.txt
        ├── guava-25.1-android.jar
        ├── guava.license
        ├── guice-4.2.2-no_aop.jar
        ├── guice.license
        ├── jansi-2.3.4.jar
        ├── jansi.license
        ├── javax.inject-1.jar
        ├── javax.inject.license
        ├── jcl-over-slf4j-1.7.32.jar
        ├── jcl-over-slf4j.license
        ├── jsoup-1.12.1.jar
        ├── jsoup.license
        ├── jsr250-api-1.0.jar
        ├── jsr250-api.license
        ├── maven-artifact-3.8.2.jar
        ├── maven-builder-support-3.8.2.jar
        ├── maven-compat-3.8.2.jar
        ├── maven-core-3.8.2.jar
        ├── maven-embedder-3.8.2.jar
        ├── maven-model-3.8.2.jar
        ├── maven-model-builder-3.8.2.jar
        ├── maven-plugin-api-3.8.2.jar
        ├── maven-repository-metadata-3.8.2.jar
        ├── maven-resolver-api-1.6.3.jar
        ├── maven-resolver-connector-basic-1.6.3.jar
        ├── maven-resolver-impl-1.6.3.jar
        ├── maven-resolver-provider-3.8.2.jar
        ├── maven-resolver-spi-1.6.3.jar
        ├── maven-resolver-transport-wagon-1.6.3.jar
        ├── maven-resolver-util-1.6.3.jar
        ├── maven-settings-3.8.2.jar
        ├── maven-settings-builder-3.8.2.jar
        ├── maven-shared-utils-3.3.4.jar
        ├── maven-slf4j-provider-3.8.2.jar
        ├── org.eclipse.sisu.inject-0.3.4.jar
        ├── org.eclipse.sisu.inject.license
        ├── org.eclipse.sisu.plexus-0.3.4.jar
        ├── org.eclipse.sisu.plexus.license
        ├── plexus-cipher-1.8.jar
        ├── plexus-cipher.license
        ├── plexus-component-annotations-2.1.0.jar
        ├── plexus-component-annotations.license
        ├── plexus-interpolation-1.25.jar
        ├── plexus-interpolation.license
        ├── plexus-sec-dispatcher-1.4.jar
        ├── plexus-sec-dispatcher.license
        ├── plexus-utils-3.2.1.jar
        ├── plexus-utils.license
        ├── slf4j-api-1.7.32.jar
        ├── slf4j-api.license
        ├── wagon-file-3.4.3.jar
        ├── wagon-http-3.4.3-shaded.jar
        └── wagon-provider-api-3.4.3.jar

8 directories, 78 files

```

