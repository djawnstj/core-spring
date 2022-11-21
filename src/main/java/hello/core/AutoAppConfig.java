package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 자동 의존성 주입
 * <ul>
 *     <li> 기본 AppConfig 에서는 @Bean 어노테이션을 이용한 수동 주입을 해줌 </li>
 *     <li> 스프링의 @ComponentScan 어노테이션을 이용하여 자동으로 의존성 주입을 해줄 수 있음 </li>
 *     <li> @ComponentScan 을 사용하면 필연적으로 @Autowired 어노테이션을 이용해 의존성 주입을 해주게 됨 <br> (@Bean 설정정보가 없어 의존성 주입을 못해주기 때문에) </li>
 *     <li> @Component 종류</li>
 *     <ol>
 *         <li> @Component </li>
 *         <li> @Controller <br> (스프링 MVC 컨트롤러로 인식) </li>
 *         <li> @Service <br> (특별한 기능은 없지만 개발자들이 비지니스 로직이 있는 레이어로 인식하는데 도움을 줌) </li>
 *         <li> @Repository <br> (스프링 데이터 접근 계층으로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다) </li>
 *         <li> @Configuration <br> (스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해줌) </li>
 *     </ol>
 * </ul>
 */
@Configuration
// @Component 어노테이션이 붙은 컴포넌트들을 자동으로 스캔해줌
@ComponentScan(
        // 스캔에서 제외할 @Component
        // FilterType 옵션
        // 1. ANNOTATION: 기본값, 어노테이션을 인식함
        //    ex) `org.example.SomAnnotation`
        // 2. ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작
        //    ex) `org.example.SomeClass`
        // 3. ASPECTJ: AspectJ 패턴 사용
        //    ex) `org.example..*Service+`
        // 4. REGEX: 정규 표현식
        //    ex) `org\.example\.Default.*`
        // 5. CUSTOM: TypeFilter 라는 인터페이스를 구현해서 처리
        //    ex) `org.example.MyTypeFilter`
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        // 스캔 범위를 명시한 패키지가 시작위치가 되고 하위 패키지까지 모두 탐색
        basePackages = { "hello.core.member", "hello.core.order" },
        // 지정한 파일이 있는 패키지가 시작위치가 되고 하위 패키지까지 모두 탐색
        basePackageClasses = AutoAppConfig.class
        // basePackages, basePackageClasses 가 없으면 @ComponentScan 어노테이션이 붙은 설정 정보 클래스의 패키지가 시작위치가 됨
        // 패키지를 지정하지 않고 설정 정보 클래스의 위치를 프로젝트 최상단에 두는것을 추천(최근 스프링 부트도 이 방법을 기본으로 제공) by 김영한
)
public class AutoAppConfig {



}
