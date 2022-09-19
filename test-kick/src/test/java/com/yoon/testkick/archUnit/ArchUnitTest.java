package com.yoon.testkick.archUnit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchUnitTest {

    @DisplayName("domain 패키지에 있는 클래스는 domain, member, study 에서 참조가능하다")
    @Test
    void packageDependencyTest_domain() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.yoon.testkick.mockito.domain");

        ArchRule domainPackageRule = classes().that().resideInAPackage("..domain..")
                .should().onlyBeAccessed()
                .byClassesThat()
                .resideInAnyPackage("..study..", "..member..", "..domain..");

        domainPackageRule.check(classes);
    }

    @DisplayName("member 패키지에 있는 클래스는 domain에서 참조하지 못한다.")
    @Test
    void packageDependencyTest_member() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.yoon.testkick.mockito.domain");

        ArchRule memberPackageRule = noClasses().that().resideInAPackage("..domain..")
                .should().accessClassesThat()
                .resideInAnyPackage("..member..");

        memberPackageRule.check(classes);
    }

    @DisplayName("study 패키지에 있는 클래스는 study에서만 참조가능하다.")
    @Test
    void packageDependencyTest_study() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.yoon.testkick.mockito.domain");

        ArchRule studyPackageRule = noClasses().that().resideOutsideOfPackage("..study..")
                .should().accessClassesThat().resideInAPackage("..study..");

        studyPackageRule.check(classes);
    }

    @DisplayName("순환참조는 없어야한다")
    @Test
    void packageDependencyTest_cycles() {
        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.yoon.testkick.mockito.domain");

        ArchRule freeOfCycles = slices().matching("..mockito.(*)..")
                .should().beFreeOfCycles();

        freeOfCycles.check(classes);
    }
}
