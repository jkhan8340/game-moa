# [GAME-MOA-API](http://ec2-52-79-151-30.ap-northeast-2.compute.amazonaws.com)
Just for me project (2023.10.13 ~ ing)
****

# 프로젝트 목적
****
- 개인 토이 프로젝트겸, 새로운 프로젝트 API 만들때 기본틀로 사용할목적
- and ... 구직하면서 간단하게 만든 기본 프로젝트입니다. 어떻게 개발하고 코딩하는지 보여줄게 없어, Link⭐️
- TODO: 게임관련 서포트 기능
  - 메이플.. 디아3.. 디아4.. 디아2.. 고민중, 피파..?
  - ex) 디아블로 랭킹관련 조회, 룬워드조합검색등 게임관련 필요데이터를 Open API 통해 데이터를 가져와 사용자 편의에 맞게 제공하는게 목적.
  - 틈틈히.. 짬날때마다 만들좌아

# 환경
****
- `Java 17` 
  - 기술지원 기간도 길고, m1지원도 되고 나이스.. spring boot3 쓸거기도 때문(default 17..)
- `Spring Boot 3`
  - 맨날 2.x번 때만썻는데 버전올려서 함써보즈아
- `JPA`
  - 이전 회사에서 일부에만 썼는데 이번엔 한번 제대로 써보자.
- `h2`
  - 서비스는 MySql로 디비 올릴생각이긴한데.. 고민중..(가난한 유부남..)
- `CI, GitHub Action`
  - GitHub을 쓰니, GitHub Action으로 한번 go!
- `CD, AWS CodeDeploy`
  - AWS ec2로 운영할꺼니깐 간편하게 쓰기위해 go!
- `redis`
  - M1, M2 관련해서 임베디드 레디스 지원안한다. 그래서, 직접다운하고 빌드해서 바이너리파일을 넣는 과정이 필요한데 난 그것도 안된다.
  - 그래서 확인해보니 MacOS 13.5.2 관련 최근에 나와 같은 유형의 문제가 있어 패지가 이루어 졌다. 참고) https://github.com/redis/redis/issues/12585
  - 결론 패치된 이후 버전 사용
# API 접속
****
[GAME-MOA-API](http://ec2-52-79-151-30.ap-northeast-2.compute.amazonaws.com)
- 계정: admin / 1234 

# 기능
****
- 현재: 
  - 회원관리 : JWT Token 기반의, 로그인 및, 사용자 정보 관련 조회, 회원가입기능 제공
- TODO:  
  - 룬워드
    - 즐겨찾기, 특정룬검색, 조합가능한 목록등 제공
  - 시즌 랭킹 조회
    - 디아블로 캐릭터 랭킹검색 및 캐릭터 검색기능 
  - 문의하기

# TODO
- 이중화, 무중단 배포
  - health check하는 방식으로 shell
