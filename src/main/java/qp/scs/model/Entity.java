package qp.scs.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qp.scs.model.api.IdEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Entity implements Serializable, qp.scs.model.api.Entity {
	protected static final Logger logger = LoggerFactory.getLogger(Entity.class);

	@Override
	public String toString() {
		Field[] fields = getAllFields(super.getClass(), null);
		StringBuilder sb = new StringBuilder();
		sb.append(super.getClass().getSimpleName() + "[");

		Object idValue = null;
		String idFieldName = null;

		for (Field field : fields) {
			// If field is an ID field
			if (field.isAnnotationPresent(Id.class)) {
				try {
					field.setAccessible(true);
					idValue = field.get(this);
					idFieldName = field.getName();

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		// This will cause 2 unsaved entities
		sb.append(idFieldName + "=" + idValue);

		// sb.delete(sb.length() - 1, sb.length());
		sb.append("]");

		return sb.toString();
	}

	protected Field[] getAllFields(Class<?> objectClass, Field[] fields) {
		Field[] declaredFields = objectClass.getDeclaredFields();
		int fieldsLen = (fields == null) ? 0 : fields.length;
		int declaredFieldsLen = (declaredFields == null) ? 0 : declaredFields.length;

		Field[] allFields = new Field[fieldsLen + declaredFieldsLen];
		if (fields != null) {
			System.arraycopy(fields, 0, allFields, 0, fieldsLen);
		}
		if (declaredFields != null) {
			System.arraycopy(declaredFields, 0, allFields, fieldsLen, declaredFieldsLen);
		}
		Class<?> superClass = objectClass.getSuperclass();
		if (!(Object.class.equals(superClass))) {
			return getAllFields(superClass, allFields);
		}

		return allFields;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if ((!(super.getClass().isAssignableFrom(o.getClass())))
				&& (!(o.getClass().isAssignableFrom(super.getClass())))) {
			return false;
		}

	

		if ((this instanceof IdEntity) && (o instanceof IdEntity)) {
			Serializable thisId = ((IdEntity) this).getId();
			Serializable oId = ((IdEntity) o).getId();
			return ((thisId != null) && (oId != null) && (thisId.equals(oId)));
		}

		boolean isEqualId = false;
		for (Field f : o.getClass().getDeclaredFields()) {
			if (f.getAnnotation(Id.class) == null) {
				continue;
			}
			try {
				f.setAccessible(true);
				if ((f.get(this) == null) || (!(f.get(this).equals(f.get(o))))) {
					return false;
				}
				isEqualId = true;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if (isEqualId) {
			return true;
		}

		String str = toString();
		if (str == null) {
			return false;
		}

		return str.equals(o.toString());
	}

	@Override
	public int hashCode() {
		int result = 17;

		if (this instanceof IdEntity && ((IdEntity) this).getId() != null) {
			Serializable thisId = ((IdEntity) this).getId();
			result = 37 * result + ((thisId == null) ? 0 : thisId.hashCode());
			return result;
		}

		Class<?> fieldType;
		for (Field field : getClass().getDeclaredFields()) {
			fieldType = field.getType();
			if (Collection.class.isAssignableFrom(fieldType)
					|| qp.scs.model.api.Entity.class.isAssignableFrom(fieldType)
					|| Logger.class.isAssignableFrom(fieldType)) {
				continue;
			}
			try {
				field.setAccessible(true);
				Object value = field.get(this);
				result = 37 * result + (value == null ? 0 : value.hashCode());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return result;
	}

}
