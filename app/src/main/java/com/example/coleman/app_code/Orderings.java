package com.example.coleman.app_code;

import java.util.Comparator;

/**
 * Created by coleman on 4/23/16.
 */
public enum Orderings implements Comparator<Todo>
{
    TIME_ASC {
        @Override
        public int compare(Todo lhs, Todo rhs)
        {
            if(lhs.dueDate == rhs.dueDate)
            {
                return 0;
            }
            else if(lhs.dueDate.before(rhs.dueDate))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    },

    TIME_DESC {
        @Override
        public int compare(Todo lhs, Todo rhs)
        {
            if(lhs.dueDate == rhs.dueDate)
            {
                return 0;
            }
            else if(lhs.dueDate.before(rhs.dueDate))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    },

    BY_CAT  {
        @Override
        public int compare(Todo lhs, Todo rhs)
        {
            if(lhs.getCategory().getid() == rhs.getCategory().getid())
            {
                return 0;
            }
            else if(lhs.getCategory().getid() < rhs.getCategory().getid())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    },

    RANDOM {
        @Override
        public int compare(Todo lhs, Todo rhs)
        {
            return 0;
        }
    };
}
