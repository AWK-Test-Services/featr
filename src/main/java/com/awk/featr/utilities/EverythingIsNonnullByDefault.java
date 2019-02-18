package com.awk.featr.utilities;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;


/**
 * Alle parameters, return types en fields in de geannoteerde class of package worden beschouwd als Nonnull tenzij anders aangegeven.
 *
 */

@Documented
@Nonnull
@TypeQualifierDefault({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EverythingIsNonnullByDefault {

}
