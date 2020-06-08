/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 *
 */

package com.microsoft.azure.management.support.v2020_04_01.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.support.v2020_04_01.ProblemClassifications;
import rx.Observable;
import rx.functions.Func1;
import java.util.List;
import com.microsoft.azure.management.support.v2020_04_01.ProblemClassification;

class ProblemClassificationsImpl extends WrapperImpl<ProblemClassificationsInner> implements ProblemClassifications {
    private final SupportManager manager;

    ProblemClassificationsImpl(SupportManager manager) {
        super(manager.inner().problemClassifications());
        this.manager = manager;
    }

    public SupportManager manager() {
        return this.manager;
    }

    private ProblemClassificationImpl wrapModel(ProblemClassificationInner inner) {
        return  new ProblemClassificationImpl(inner, manager());
    }

    @Override
    public Observable<ProblemClassification> listAsync(String serviceName) {
        ProblemClassificationsInner client = this.inner();
        return client.listAsync(serviceName)
        .flatMap(new Func1<List<ProblemClassificationInner>, Observable<ProblemClassificationInner>>() {
            @Override
            public Observable<ProblemClassificationInner> call(List<ProblemClassificationInner> innerList) {
                return Observable.from(innerList);
            }
        })
        .map(new Func1<ProblemClassificationInner, ProblemClassification>() {
            @Override
            public ProblemClassification call(ProblemClassificationInner inner) {
                return wrapModel(inner);
            }
        });
    }

    @Override
    public Observable<ProblemClassification> getAsync(String serviceName, String problemClassificationName) {
        ProblemClassificationsInner client = this.inner();
        return client.getAsync(serviceName, problemClassificationName)
        .flatMap(new Func1<ProblemClassificationInner, Observable<ProblemClassification>>() {
            @Override
            public Observable<ProblemClassification> call(ProblemClassificationInner inner) {
                if (inner == null) {
                    return Observable.empty();
                } else {
                    return Observable.just((ProblemClassification)wrapModel(inner));
                }
            }
       });
    }

}