## 📌미션 2

> 다양한 트랜잭션 상태와, 트랜잭션 전파에 대해 조사해주세요!

### 트랜잭션(Transaction)이란?
***"더이상 분할이 불가능한 업무처리의 단위"***
트랜잭션이란 데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위이다. 즉, 한꺼번에 수행되어야 할 일련의 연산 모음을 의미한다. 

ex) 계좌이체는 인출과 입금 두 과정으로 이루어지는데, 이 두 과정은 동시에 성공하던지 동시에 실패해야 한다. 
<br>


### 트랜잭션의 특징(ACID)
1. **원자성(Atomicity)**: 트랜잭션이 데이터베이스에 모두 반영되던가, 아니면 전혀 반영되지 않아야 한다.
2. **일관성(Consistency)**: 트랜잭션의 작업 처리 결과가 항상 일관성이 있어야 한다.
3. **격리성(Isolation)**: 둘 이상의 트랜잭션이 동시에 실행될 경우, 어떤 하나의 트랜잭션이라도 다른 트랜잭션의 연산에 끼어들 수 없다.
    
4. **지속성(Durability)**: 결과는 영구적으로 반영되어야 한다.
<br>

### 트랜잭션의 상태
트랜잭션은 다음과 같이 5가지 상태를 가질 수 있다.
![](https://velog.velcdn.com/images/heeeeeyeon/post/e48207da-1b1b-49f9-84d7-175c987e0657/image.png)

1. **활성화(Active)**: 트랜잭션이 정상적으로 실행중인 상태
2. **부분 완료(Partially commited)**: 트랜잭션이 모든 작업을 수행하였지만, 아직 Commit 연산이 실행되기 직전의 상태
3. **완료(Commited)**: 트랜잭션이 성공적으로 모든 작업을 완료하고, Commit 연산을 실행한 후의 상태
4. **실패(Failed)**: 트랜잭션 실행에 오류가 발생하여 중단된 상태
5. **철회(Aborted)**: 트랜잭션이 비정상적으로 종료되어 Rollback 연산을 수행한 상태

>**트랜잭션의 연산**
* `Commit` : 모든 작업들을 정상 처리하겠다고 확정하는 명령어로, 해당 처리 과정을 DB에 영구 저장함.
* `Rollback`: 트랜잭션의 처리 과정에서 발생한 변경사항을 취소하는 명령어로, 트랜잭션을 시작되기 이전의 상태로 되돌아감.

<br>

### 트랜잭션 전파(Propagation)

#### 트랜잭션 전파란?
하나의 트랜잭션이 실행되고 있는 도중에 다른 새로운 트랜잭션이 실행되는 경우, 어떻게 동작할지 결정하는 것을 트랜잭션 전파(propagation)라 한다.

#### 외부 트랜잭션과 내부 트랜잭션
![](https://velog.velcdn.com/images/heeeeeyeon/post/95076d63-5d2d-4390-81e5-4ed9592cd3b7/image.png)
<br>
외부 트랜잭션이란 처음 시작된 트랜잭션을 의미하고 내부 트랜잭션은 외부에 트랜잭션이 수행되고 있는 도중에 호출된 트랜잭션을 의미한다.
위 그림은 외부 트랜잭션이 수행중이고, 아직 끝나지 않았는데 내부 트랜잭션이 수행되는 모습이다.

![](https://velog.velcdn.com/images/heeeeeyeon/post/88a835f0-b060-4c8c-92ea-2e5726ca3249/image.png)
<br>
스프링은 위와 같이 외부 트랜잭션과 내부 트랜잭션을 묶어 하나의 트랜잭션으로 만들어준다. 

#### 물리 트랜잭션과 논리 트랜잭션
![](https://velog.velcdn.com/images/heeeeeyeon/post/675bf5a2-bebe-4e1c-a81f-317aaabfd771/image.png)
<br>
스프링은 논리 트랜잭션과 물리 트랜잭션이라는 개념을 사용한다.
위 그림과 같이 두 트랜잭션은 하나의 물리 트랜잭션으로 묶이고, 하나의 물리 트랜잭션 내 각각의 트랜잭션은 논리 트랜잭션으로 구분한다. 
<br>
이 때 **두 가지의 원칙**이 있다. 
1. 모든 논리 트랜잭션이 커밋되어야 물리 트랜잭션이 커밋된다.
2. 하나의 논리 트랜잭션이라도 롤백되면 물리 트랜잭션은 롤백된다.

예를 들어 보자. 
<br>
![](https://velog.velcdn.com/images/heeeeeyeon/post/0cd477d0-b9a2-424a-b3d3-d2fac88b09a9/image.png)
<br>
위 그림은 원칙1에 의해 모든 논리 트랜잭션이 커밋 되었으므로 물리 트랜잭션도 커밋된 모습이다.

![](https://velog.velcdn.com/images/heeeeeyeon/post/765b9f54-36e9-4953-bc4a-cf166b31ace3/image.png)
<br>
위 그림은 외부 논리 트랜잭션이 롤백 되었으므로 원칙 2에 의해 물리 트랜잭션도 롤백된 모습이다.

![](https://velog.velcdn.com/images/heeeeeyeon/post/28f3c7cd-b506-4419-b3f2-b60d59718b4f/image.png)
<br>
이는 내부 논리 트랜잭션이 롤백 되었으므로 원칙 2에 의해 물리 트랜잭션도 롤백된 모습이다. 


---

## 📌미션 3
>함수 기반 인덱스와 복합 인덱스에 대해 조사하고, 성능상 이점과 단점을 적어주세요!

### 함수 기반 인덱스
함수 기반 인덱스란 데이터베이스에서 특정 열이나 표현식의 값을 기반으로 인덱스를 생성하는 것을 말한다.

함수 기반 인덱스는 다음 2가지 방법으로 구현할 수 있다.

1. **가상 칼럼을 이용한 인덱스**
```sql
CREATE TABLE user(
   user_id BIG_INT
   first_name VARCHAR(10),
   last_name VARCHAR(10),
   PRIMARY KEY (user_id)
);
```
만약 first_name과 last_name을 합쳐 검색해야 한다면 두 칼럼을 합친 full_name이라는 칼럼을 추가하는 방법도 있지만 MySQL 8.0부터는 아래와 같이 가상 컬럼을 추가하고 가상 컬럼에 인덱스를 생성할 수 있다.
```sql
ALTER TABLE user
	ADD full_name VARCHAR(20) AS (CONCAT(first_name, ' ' ,last_name)) VIRTUAL,
	ADD INDEX ix_fullname (full_name);
```

<br>

2. **함수를 이용한 인덱스**
MySQL 8.0부터는 다음과 같이 테이블 구조를 변경하지 않고 함수를 이용해 인덱스를 생성할 수도 있다.
```sql
CREATE TABLE user(
    user_id BIGINT,
    first_name VARCHAR(10),
    last_name VARCHAR(10),
    PRIMARY KEY (user_id),
    INDEX ix_fullname ((CONCAT(first_name, ' ', last_name)))
);
```

#### 함수 기반 인덱스의 장점
1. 성능 향상: 쿼리의 응답 시간을 줄일 수 있다. 
2. 쿼리 최적화: 옵티마이저가 함수 결과 인덱스를 활용해 더 좋은 실행계획을 선택할 수 있다. 
3. 유연성: 다양한 함수 및 표현식에 대한 인덱스 생성을 지원한다. 


#### 함수 기반 인덱스의 단점
1. 인덱스 유지 비용: 칼럼의 데이터가 변경될 때마다 함수를 계산하고 인덱스를 업데이트 해야 한다. 
2. 저장 공간: 함수 결과값을 별도로 보관해야 한다.
3. 복잡성 증가: 잘못 설계하면 오히려 성능이 느려질 수 있다.

<br>
<br>

### 복합 인덱스
복합 인덱스란 데이터베이스에서 여러 개의 컬럼(열)들을 조합하여 인덱스를 생성하는 것을 말한다.

#### 복합 인덱스의 장점
1. 검색 속도 개선
2. 데이터 정렬의 효율성
3. 인덱스의 용량 절감
4. 쿼리 최적화

#### 복합 인덱스의 단점
1. 복합 인덱스는 일반적으로 WHERE 절에 자주 사용되는 컬럼들로 구성된다. 이 때, 인덱스를 생성하는 컬럼의 개수가 많아질수록 인덱스의 성능은 떨어질 수 있다.
2. 복합 인덱스를 생성할 때는 인덱스 생성 순서도 고려해야 한다. 주로 자주 이용되는 순서대로 복합 인덱스 컬럼의 순서를 결정한다.

   ---
#### 📖참고자료
https://inpa.tistory.com/entry/MYSQL-%F0%9F%93%9A-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98Transaction-%EC%9D%B4%EB%9E%80-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC#%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98_%ED%8A%B9%EC%A7%95
https://sugoring-it.tistory.com/227#google_vignette
https://jaehee1007.tistory.com/77
https://rlaehddnd0422.tistory.com/100
https://hstory0208.tistory.com/entry/MySQL%EC%9D%98-%EC%9D%B8%EB%8D%B1%EC%8A%A4-%EC%A2%85%EB%A5%98%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
https://velog.io/@kwontae1313/%EB%B3%B5%ED%95%A9%EC%9D%B8%EB%8D%B1%EC%8A%A4
https://dataway.co.kr/%ED%95%A8%EC%88%98-%EA%B8%B0%EB%B0%98-%EC%9D%B8%EB%8D%B1%EC%8A%A4-%EC%9E%A5%EC%A0%90-%EB%B0%8F-%EB%8B%A8%EC%A0%90/
<br>
트랜잭션 전파 관련 자료들은 김영한 강사님의 스프링 DB 2편 자료를 참고하였습니다.
