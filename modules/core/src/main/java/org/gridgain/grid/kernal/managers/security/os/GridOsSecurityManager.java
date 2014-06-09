/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.managers.security.os;

import org.gridgain.grid.*;
import org.gridgain.grid.kernal.*;
import org.gridgain.grid.kernal.managers.*;
import org.gridgain.grid.kernal.managers.security.*;
import org.gridgain.grid.security.*;
import org.gridgain.grid.spi.authentication.*;
import org.gridgain.grid.util.typedef.*;
import org.jetbrains.annotations.*;

import java.net.*;
import java.util.*;

/**
 * No-op implementation for {@link GridSecurityManager}.
 */
public class GridOsSecurityManager extends GridNoopManagerAdapter implements GridSecurityManager {
    /**
     * @param ctx Kernal context.
     */
    public GridOsSecurityManager(GridKernalContext ctx) {
        super(ctx);
    }

    /** Allow all permissions. */
    private static final GridSecurityPermissionSet ALLOW_ALL = new GridAllowAllPermissionSet();

    /** {@inheritDoc} */
    @Override public boolean securityEnabled() {
        return false;
    }

    /** {@inheritDoc} */
    @Override public GridSecurityContext authenticateNode(GridNode node, GridSecurityCredentials cred)
        throws GridException {
        GridSecuritySubjectAdapter s = new GridSecuritySubjectAdapter(GridSecuritySubjectType.REMOTE_NODE, node.id());

        s.address(new InetSocketAddress(F.first(node.addresses()), 0));

        s.permissions(ALLOW_ALL);

        return new GridSecurityContext(s);
    }

    /** {@inheritDoc} */
    @Override public GridSecurityContext authenticate(GridAuthenticationContext ctx) throws GridException {
        GridSecuritySubjectAdapter s = new GridSecuritySubjectAdapter(ctx.subjectType(), ctx.subjectId());

        s.permissions(ALLOW_ALL);
        s.address(ctx.address());

        return new GridSecurityContext(s);
    }

    /** {@inheritDoc} */
    @Override public Collection<GridSecuritySubject> authenticatedNodes() {
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override public GridSecuritySubject authenticatedNode(UUID nodeId) {
        return null;
    }

    /** {@inheritDoc} */
    @Override public void authorize(String name, GridSecurityPermission perm, @Nullable GridSecurityContext securityCtx)
        throws GridSecurityException {
        // No-op.
    }
}