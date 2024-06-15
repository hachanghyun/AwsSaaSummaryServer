## 1. SERVER Deployment URL
  https://github.com/hachanghyun/awsSaaSumServer

## 2. FrontEnd Deployment URL
  https://github.com/hachanghyun/AwsSaaSumApp

# awsSaaSummaryServer 

## elastic beanstalk 적용 
  SSL 적용, 기존APP에서는 HTTP통신 안됨, HTTPS 통신만 가능!!!
  도메인 가비아에서 구매후 -> route53에 도메인 등록후 -> AWS Certificate Manager(ACM) 에 route 53에 서브도메인 등록 해두면, 그 도메인 4개의 주소로 인증을 하는 방식!! -> elasticbeanstak에서도 https 적용 시켜준다음에 진행 하면 됨
### 포트 nginx 5000번으로 적용

### aws EC2 ssh 접속하는법
  ssh -i pem키위치 ec2의 탄력적ip주소

### elastic beanstalk
  clinet -> EC2 -> RDS
  EC2 보안그룹에 RDB 보안그룹 등록해야함

### zsh 설정법
  vim ~/.zshrc
  
