package com.hurley.codehub.bean;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.hurley.codehub.utils.GsonUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * <pre>
 *      @author hurley
 *      date   : 2018/11/19 下午8:10
 *      github : https://github.com/HurleyJames
 *      desc   : 体系实体类
 * </pre>
 */
@Route(path = "/service/json")
public class SystemBean implements SerializationService {

    private int id;
    private int courseId;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;
    private List<Children> children;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return GsonUtil.convertObj(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return GsonUtil.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return GsonUtil.convertObj(input, clazz);
    }

    @Override
    public void init(Context context) {

    }

    public static class Children implements SerializationService{
        /**
         * children : []
         * courseId : 13
         * id : 60
         * name : Android Studio相关
         * order : 1000
         * parentChapterId : 150
         * userControlSetTop : false
         * visible : 1
         */

        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;
        private List<Children> children;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        @Override
        public <T> T json2Object(String input, Class<T> clazz) {
            return GsonUtil.convertObj(input, clazz);
        }

        @Override
        public String object2Json(Object instance) {
            return GsonUtil.toJson(instance);
        }

        @Override
        public <T> T parseObject(String input, Type clazz) {
            return GsonUtil.convertObj(input, clazz);
        }

        @Override
        public void init(Context context) {

        }

        public Children(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
