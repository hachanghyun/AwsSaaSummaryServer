package com.hotong.awsSaaSummaryServer.chatGpt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubtopic is a Querydsl query type for Subtopic
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubtopic extends EntityPathBase<Subtopic> {

    private static final long serialVersionUID = 1712361406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubtopic subtopic = new QSubtopic("subtopic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTopic topic;

    public QSubtopic(String variable) {
        this(Subtopic.class, forVariable(variable), INITS);
    }

    public QSubtopic(Path<? extends Subtopic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubtopic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubtopic(PathMetadata metadata, PathInits inits) {
        this(Subtopic.class, metadata, inits);
    }

    public QSubtopic(Class<? extends Subtopic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.topic = inits.isInitialized("topic") ? new QTopic(forProperty("topic")) : null;
    }

}

