/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.testing.jacoco.tasks;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.Incubating;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.ClosureBackedAction;
import org.gradle.api.internal.project.IsolatedAntBuilder;
import org.gradle.api.reporting.Reporting;
import org.gradle.api.specs.Spec;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.internal.jacoco.AntJacocoReport;
import org.gradle.internal.jacoco.JacocoReportsContainerImpl;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension;

import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Task to generate HTML, Xml and CSV reports of Jacoco coverage data.
 */
@CacheableTask
@Incubating
public class JacocoReport extends JacocoBase implements Reporting<JacocoReportsContainer> {

    private final JacocoReportsContainer reports;

    private FileCollection executionData;
    private FileCollection sourceDirectories;
    private FileCollection classDirectories;
    private FileCollection additionalClassDirs;
    private FileCollection additionalSourceDirs;

    public JacocoReport() {
        reports = getInstantiator().newInstance(JacocoReportsContainerImpl.class, this);
        onlyIf(new Spec<Task>() {
            @Override
            public boolean isSatisfiedBy(Task element) {
                //TODO SF it should be 'any' instead of 'all'
                return Iterables.all(getExecutionData(), new Predicate<File>() {
                    @Override
                    public boolean apply(File file) {
                        return file.exists();
                    }

                });
            }

        });
    }

    @Inject
    protected Instantiator getInstantiator() {
        throw new UnsupportedOperationException();
    }

    @Inject
    protected IsolatedAntBuilder getAntBuilder() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the reports to be generated by this task.
     */
    @Nested
    @Override
    public JacocoReportsContainer getReports() {
        return reports;
    }

    /**
     * Configures the reports to be generated by this task.
     */
    @Override
    public JacocoReportsContainer reports(Closure closure) {
        return reports(new ClosureBackedAction<JacocoReportsContainer>(closure));
    }

    @Override
    public JacocoReportsContainer reports(Action<? super JacocoReportsContainer> configureAction) {
        configureAction.execute(reports);
        return reports;
    }

    /**
     * Collection of execution data files to analyze.
     */
    @PathSensitive(PathSensitivity.NONE)
    @InputFiles
    public FileCollection getExecutionData() {
        return executionData;
    }

    public void setExecutionData(FileCollection executionData) {
        this.executionData = executionData;
    }

    /**
     * Source sets that coverage should be reported for.
     */
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    public FileCollection getSourceDirectories() {
        return sourceDirectories;
    }

    public void setSourceDirectories(FileCollection sourceDirectories) {
        this.sourceDirectories = sourceDirectories;
    }

    /**
     * Source sets that coverage should be reported for.
     */
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    public FileCollection getClassDirectories() {
        return classDirectories;
    }

    public void setClassDirectories(FileCollection classDirectories) {
        this.classDirectories = classDirectories;
    }

    /**
     * Additional class dirs that coverage data should be reported for.
     */
    @Optional
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    public FileCollection getAdditionalClassDirs() {
        return additionalClassDirs;
    }

    public void setAdditionalClassDirs(FileCollection additionalClassDirs) {
        this.additionalClassDirs = additionalClassDirs;
    }

    /**
     * Additional source dirs for the classes coverage data is being reported for.
     */
    @Optional
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    public FileCollection getAdditionalSourceDirs() {
        return additionalSourceDirs;
    }

    public void setAdditionalSourceDirs(FileCollection additionalSourceDirs) {
        this.additionalSourceDirs = additionalSourceDirs;
    }

    @TaskAction
    public void generate() {
        Spec<File> fileExistsSpec = new Spec<File>() {
            @Override
            public boolean isSatisfiedBy(File file) {
                return file.exists();
            }
        };
        new AntJacocoReport(getAntBuilder()).execute(
            getJacocoClasspath(),
            getProject().getName(),
            getAllClassDirs().filter(fileExistsSpec),
            getAllSourceDirs().filter(fileExistsSpec),
            getExecutionData(),
            getReports()
        );
    }

    /**
     * Adds execution data files to be used during coverage analysis.
     *
     * @param files one or more files to add
     */
    public void executionData(Object... files) {
        if (executionData == null) {
            executionData = getProject().files(files);
        } else {
            executionData = executionData.plus(getProject().files(files));
        }
    }

    /**
     * Adds execution data generated by a task to the list of those used during coverage analysis. Only tasks with a {@link JacocoTaskExtension} will be included; all others will be ignored.
     *
     * @param tasks one or more tasks to add
     */
    public void executionData(Task... tasks) {
        for (Task task : tasks) {
            final JacocoTaskExtension extension = task.getExtensions().findByType(JacocoTaskExtension.class);
            if (extension != null) {
                executionData(new Callable<File>() {
                    @Override
                    public File call() {
                        return extension.getDestinationFile();
                    }
                });
                mustRunAfter(task);
            }
        }
    }

    /**
     * Adds execution data generated by the given tasks to the list of those used during coverage analysis. Only tasks with a {@link JacocoTaskExtension} will be included; all others will be ignored.
     *
     * @param tasks one or more tasks to add
     */
    public void executionData(TaskCollection tasks) {
        tasks.all(new Action<Task>() {
            @Override
            public void execute(Task task) {
                executionData(task);
            }
        });
    }

    /**
     * Gets the class directories that coverage will be reported for. All classes in these directories will be included in the report.
     *
     * @return class dirs to report coverage of
     */
    @Internal
    public FileCollection getAllClassDirs() {
        FileCollection additionalDirs = getAdditionalClassDirs();
        if (additionalDirs == null) {
            return classDirectories;
        }
        return classDirectories.plus(getAdditionalClassDirs());
    }

    /**
     * Gets the source directories for the classes that will be reported on. Source will be obtained from these directories only for the classes included in the report.
     *
     * @return source directories for the classes reported on
     * @see #getAllClassDirs()
     */
    @Internal
    public FileCollection getAllSourceDirs() {
        FileCollection additionalDirs = getAdditionalSourceDirs();
        if (additionalDirs == null) {
            return sourceDirectories;
        }
        return sourceDirectories.plus(getAdditionalSourceDirs());
    }

    /**
     * Adds a source set to the list to be reported on. The output of this source set will be used as classes to include in the report. The source for this source set will be used for any classes
     * included in the report.
     *
     * @param sourceSets one or more source sets to report on
     */
    public void sourceSets(final SourceSet... sourceSets) {
        getProject().afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                for (SourceSet sourceSet : sourceSets) {
                    if (getSourceDirectories() == null) {
                        setSourceDirectories(getProject().files(sourceSet.getAllJava().getSrcDirs()));
                    } else {
                        setSourceDirectories(getSourceDirectories().plus(getProject().files(sourceSet.getAllJava().getSrcDirs())));
                    }
                    if (getClassDirectories() == null) {
                        setClassDirectories(sourceSet.getOutput());
                    } else {
                        setClassDirectories(getClassDirectories().plus(sourceSet.getOutput()));
                    }
                }
            }
        });
    }

    /**
     * Adds additional class directories to those that will be included in the report.
     *
     * @param dirs one or more directories containing classes to report coverage of
     */
    public void additionalClassDirs(File... dirs) {
        additionalClassDirs(getProject().files(Arrays.asList(dirs)));
    }

    /**
     * Adds additional class directories to those that will be included in the report.
     *
     * @param dirs a {@code FileCollection} of directories containing classes to report coverage of
     */
    public void additionalClassDirs(FileCollection dirs) {
        if (additionalClassDirs == null) {
            additionalClassDirs = dirs;
        } else {
            additionalClassDirs = additionalClassDirs.plus(dirs);
        }
    }

    /**
     * Adds additional source directories to be used for any classes included in the report.
     *
     * @param dirs one or more directories containing source files for the classes included in the report
     */
    public void additionalSourceDirs(File... dirs) {
        additionalSourceDirs(getProject().files(Arrays.asList(dirs)));
    }

    /**
     * Adds additional source directories to be used for any classes included in the report.
     *
     * @param dirs a {@code FileCollection} of directories containing source files for the classes included in the report
     */
    public void additionalSourceDirs(FileCollection dirs) {
        if (additionalSourceDirs == null) {
            additionalSourceDirs = dirs;
        } else {
            additionalSourceDirs = additionalSourceDirs.plus(dirs);
        }
    }
}
