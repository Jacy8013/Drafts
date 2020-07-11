package io.jacy.drafts;

/**
 * {@code
 *         <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-context</artifactId>
 *             <version>5.2.7.RELEASE</version>
 *             <exclusions>
 *                 <exclusion>
 *                     <groupId>org.springframework</groupId>
 *                     <artifactId>spring-jcl</artifactId>
 *                 </exclusion>
 *             </exclusions>
 *         </dependency>
 * }
 *
 * Maven依赖层级关系中, 如果不需要用到后续依赖(三级/四级...n级), 可以直接在顶层配置 exclusions
 *
 * 项目打包为war, 也是为了测试该打包规则
 *
 * @author Jacy
 */
public class MavenExclusion {

}
