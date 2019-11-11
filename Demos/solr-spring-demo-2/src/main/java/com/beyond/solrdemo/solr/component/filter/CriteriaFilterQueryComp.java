package com.beyond.solrdemo.solr.component.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Crotch;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.Node;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author beyondlov1
 * @date 2019/11/11
 */
public class CriteriaFilterQueryComp extends FilterQueryComp {
    private Criteria criteria;

    public CriteriaFilterQueryComp(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    protected void init() {
        super.init();
        Field field = criteria.getField();
        Set<Criteria.Predicate> predicates = criteria.getPredicates();
        Collection<Criteria> siblings = criteria.getSiblings();
    }

    private static String getNodeStr(Node node) {
        if (node instanceof Crotch) {
            Collection<Criteria> siblings = node.getSiblings();
            StringBuilder sb = new StringBuilder();
            sb.append(node.isOr() ? " or " : " and " );
            sb.append("(");
            for (Criteria sibling : siblings) {
                sb.append(getNodeStr(sibling));
            }
            sb.append(")");
            return sb.toString().replace("( and ","(");
        } else if (node instanceof Criteria) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.isOr() ? " or " : " and ");
            Set<Criteria.Predicate> predicates = ((Criteria) node).getPredicates();
            sb.append("(");
            for (Criteria.Predicate predicate : predicates) {
                if (ObjectUtils.nullSafeEquals(Criteria.OperationKey.EQUALS.getKey(), predicate.getKey())) {
                    sb.append(((Criteria) node).getField());
                    sb.append(":");
                    sb.append(predicate.getValue());
                    sb.append(" or  ");
                }
            }
            String substring = sb.substring(0, sb.length() - 5);
            return substring+")";
        }
        return null;
    }

    public static void main(String[] args) {
        Criteria criteria1E = new Criteria("e").is("e").and("g").is("g");
        criteria1E.and("h").is("h");

        Criteria criteria = new Criteria(Criteria.WILDCARD);
        criteria.is("c")
                .and("b").in("bcc", "fege")
                .and("d").is("d")
                .or(criteria1E)
                .or(new Criteria("f").is("f"));

        Criteria criteria1 = new Criteria("ccc");
        criteria1.is("cc");
        criteria.or(criteria1);

        StringBuilder sb = new StringBuilder();
        Node parent = criteria.getParent();
        while (parent != null) {
            sb.append(getNodeStr(parent));
            parent = parent.getParent();
        }

        if (StringUtils.startsWith(sb, " and ")) {
            String s = sb.substring(5, sb.length());
            System.out.println(s);
        }else {
            System.out.println(sb);
        }
    }
}
