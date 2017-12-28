Esper PoC
==
Esper is software for complex event processing (CEP) and streaming analytics, available for .NET as NEsper.
- EPL(Event Processing Language) 제공
- Dual CPU * 2 GHz : 초당 50만건 이상의 처리 성능과 평균 3 microseconds 이하로 처리

> CEP Architecture
![CEP 아키텍처](http://hochul.net/blog/wp-content/uploads/2012/12/cep_architecture.png)

> Esper Architecture
![Esper 아키텍처](http://sungsoo.github.io/images/esper-architecture.png)


## Reference Site
1. [Esper Homepage](http://www.espertech.com/)
2. [Data stream management system](https://en.wikipedia.org/wiki/Data_stream_management_system)
3. [Complex Event Processing with Esper](https://www.slideshare.net/tedwon/complex-event-processing-with-esper-72445589)
4. [Rule Engine Evaluation for Complex Event Processing](https://www.slideshare.net/ChandraDivi1/rule-engine-evaluation-for-complex-event-processing)
5. [오픈 소스로 구현하는 실시간 데이터 처리를 위한 CEP](https://www.slideshare.net/kthcorp/b2-realtimecepv26)
6. [Storm-ESPER 사용법](http://blog.embian.com/91)
   
## Install & Environments
1. 설치 환경: Window 7 (64bits)
2. 개발 환경: Eclipse Oxygen 
3. Esper 다운로드: http://www.espertech.com/download/ (회원가입 필요, maven 사용시 생략)
4. Eclispe Maven Project 생성
   pom.xml
~~~
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.isd.esper</groupId>
  <artifactId>esper</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>esper</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
   <!-- https://mvnrepository.com/artifact/com.espertech/esper -->
   <dependency>
    <groupId>com.espertech</groupId>
    <artifactId>esper</artifactId>
    <version>7.0.0</version>
   </dependency>
    
  </dependencies>
  
</project>
~~~
5. Eclipse java Project에 Esper 라이브러리 추가  
   -> JAVA 프로젝트 생성 -> 마우스 오른쪽 버튼 -> Properties  
   -> Java Build Path -> Libraries -> Add External JARs...  
   -> Esper 다운로드 파일(ex. esper 라이브러리)을 추가  
   lib/esper/esper/lib/cglib-nodep-2.2.jar  
   lib/esper/esper/lib/antlr-runtime-3.2.jar  
   lib/esper/esper-4.10.0.jar  
   lib/esper/esper/lib/commons-logging-1.1.1.jar  
   lib/esper/esper/lib/log4j-1.2.16.jar
6. Java source 컴파일 후 실행 : SampleEngine.java, SampleEvent.java, SampleListener.java
   
## TroubleShootings
1. Maven Proxy 설치 : 사용자 Directory 에서 .m2
