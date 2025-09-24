- **SOLID**
    
    - **S** — Single Responsibility Principle (단일 책임 원칙)
        - 한 클래스는 하나의 책임(변화 이유)만 가져야 한다.
    - **O** — Open/Closed Principle (개방-폐쇄 원칙)
        - 확장에는 열려 있고(extend), 수정에는 닫혀 있어야(modify) 한다.
    - **L** — Liskov Substitution Principle (리스코프 치환 원칙)
        - 서브타입은 기반 타입 대신 사용해도 기능적으로 문제가 없어야 한다.
    - **I** — Interface Segregation Principle (인터페이스 분리 원칙)
        - 클라이언트는 자신이 사용하지 않는 메서드에 의존하지 않아야 한다. (작은 인터페이스 여러 개가 낫다)
    - **D** — Dependency Inversion Principle (의존성 역전 원칙)
        - 고수준 모듈(정책)은 저수준 모듈(세부) 둘 다 추상에 의존해야 한다. 구체(implementation)에 의존하면 안 된다.
- **DI**
    
    Dependency Injection은 “객체가 스스로 필요한 의존(다른 객체)을 생성하지 않고 외부에서 주입받는 설계 방식이다.
    
    모듈간 결합도를 낮추고 교체·테스트·구성 관리를 쉽게한다.
    
    DI는 더 넓은 개념인 IoC(제어의 역전, Inversion of Control)의 한 구현 방식이다.
    
- **IoC**
    
    IoC는 어떤 기능(또는 제어 흐름)을 호출하던 쪽이 아니라 외부가 제어권을 넘겨주는 설계이다.
    
    쉽게 말해, _내가 다른 모듈을 직접 호출하지 않고, 외부에서 내가 호출되도록 설계하는 것_
    
- **생성자 주입 vs 수정자, 필드 주입 차이**
    
    - **생성자 주입(Constructor Injection)**
        
        객체가 생성될 때(생성자 파라미터로) 의존성을 전달받음. 불변(immutable) 필드로 보장 가능.
        
    - **수정자 주입(Setter / Property Injection)**
        
        객체를 생성한 뒤 세터 메서드나 속성으로 의존성을 주입. 선택적(옵션) 의존성에 적합.
        
    - **필드 주입(Field Injection)**
        
        의존성을 직접 필드에 주입(예: 리플렉션으로 private 필드에 주입). 프레임워크에서 흔히 사용되지만 테스트·투명성에서 단점.
        
- **AOP**
    
    AOP는 횡단 관심사(cross-cutting concerns) 로깅, 트랜잭션, 보안, 성능계측 등을 핵심 비즈니스 로직과 분리해 재사용·관리하기 위한 기법이다.
    
- **서블릿**
    
    서블릿은 자바로 작성된 서버측 컴포넌트로, 서블릿 컨테이너(예: Tomcat)가 HTTP 요청을 전달하면 이를 처리해 HTTP 응답을 생성, 컨테이너가 로드·초기화·요청별 호출(`doGet`/`doPost`)·종료를 관리하여 웹 애플리케이션의 엔트리 포인트 역할을 한다.