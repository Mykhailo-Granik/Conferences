package com.mgranik.conferences;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.mgranik.conferences",
        importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeArchives.class})
public class HexagonalDesignRulesTest {

    private static final String SERVICE = "service";
    private static final String ADAPTOR_API = "api-adapter";


    @ArchTest
    ArchRule packages_should_follow_hexagonal_design = layeredArchitecture()
            .consideringAllDependencies()
            .as("Packages structure should match hexagonal design rules")
            .layer(SERVICE).definedBy("..service..")
            .layer(ADAPTOR_API).definedBy("..controller..")
            .whereLayer(SERVICE).mayOnlyBeAccessedByLayers(ADAPTOR_API);

}
