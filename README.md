# html parser
입력받은 url을 파싱하는 프로그램 

## 개발 환경
* Spring boot 2.1.0
* jsoup 1.11.3
* lombok 1.2.3
* thymeleaf 5.3.0
* jacoco 0.8.2

## 빌드
* git clone https://github.com/backsu83/html-parser.git
* cd html-parser
* ./gradlew build bootRun

## 테스트
* http://localhost:5000/
* 테스트 커버리지 확인 : jacoco 파일경로 
* project -> Bulid -> jacocoHtml -> index.html
