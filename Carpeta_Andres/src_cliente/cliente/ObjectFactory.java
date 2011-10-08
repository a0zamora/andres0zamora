
package cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cliente package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MandarSolicitudTruequeResponse_QNAME = new QName("http://servidor/", "mandarSolicitudTruequeResponse");
    private final static QName _FiltrarCartasResponse_QNAME = new QName("http://servidor/", "filtrarCartasResponse");
    private final static QName _EliminarRespuestaResponse_QNAME = new QName("http://servidor/", "eliminarRespuestaResponse");
    private final static QName _ComercioComputadora_QNAME = new QName("http://servidor/", "comercioComputadora");
    private final static QName _RetornarCartasResponse_QNAME = new QName("http://servidor/", "retornarCartasResponse");
    private final static QName _MandarSolicitudTrueque_QNAME = new QName("http://servidor/", "mandarSolicitudTrueque");
    private final static QName _RetornarCartasOfertadasSistemas_QNAME = new QName("http://servidor/", "retornarCartasOfertadasSistemas");
    private final static QName _ComercioJugadoresResponse_QNAME = new QName("http://servidor/", "comercioJugadoresResponse");
    private final static QName _Publicidad_QNAME = new QName("http://servidor/", "publicidad");
    private final static QName _ComercioJugadores_QNAME = new QName("http://servidor/", "comercioJugadores");
    private final static QName _RetornaCartaResponse_QNAME = new QName("http://servidor/", "retornaCartaResponse");
    private final static QName _ConsultarPerfil_QNAME = new QName("http://servidor/", "consultarPerfil");
    private final static QName _Guardar_QNAME = new QName("http://servidor/", "guardar");
    private final static QName _ConsultarCartasSeleccionadasResponse_QNAME = new QName("http://servidor/", "consultarCartasSeleccionadasResponse");
    private final static QName _EliminarMensajeConfirmacion_QNAME = new QName("http://servidor/", "eliminarMensajeConfirmacion");
    private final static QName _EliminarRespuesta_QNAME = new QName("http://servidor/", "eliminarRespuesta");
    private final static QName _DesconectarResponse_QNAME = new QName("http://servidor/", "desconectarResponse");
    private final static QName _RetornarCartasOfertadasSistemasResponse_QNAME = new QName("http://servidor/", "retornarCartasOfertadasSistemasResponse");
    private final static QName _RetornarCartas_QNAME = new QName("http://servidor/", "retornarCartas");
    private final static QName _RetornarCartasOfertadasResponse_QNAME = new QName("http://servidor/", "retornarCartasOfertadasResponse");
    private final static QName _ConsultarCartasSeleccionadas_QNAME = new QName("http://servidor/", "consultarCartasSeleccionadas");
    private final static QName _RetirarCartaOfertada_QNAME = new QName("http://servidor/", "retirarCartaOfertada");
    private final static QName _ConsultarResponse_QNAME = new QName("http://servidor/", "consultarResponse");
    private final static QName _Desconectar_QNAME = new QName("http://servidor/", "desconectar");
    private final static QName _Consultar_QNAME = new QName("http://servidor/", "consultar");
    private final static QName _OfertarCartas_QNAME = new QName("http://servidor/", "ofertarCartas");
    private final static QName _RetirarCartaOfertadaResponse_QNAME = new QName("http://servidor/", "retirarCartaOfertadaResponse");
    private final static QName _OfertarCartasResponse_QNAME = new QName("http://servidor/", "ofertarCartasResponse");
    private final static QName _RetornaCarta_QNAME = new QName("http://servidor/", "retornaCarta");
    private final static QName _ConsultarPerfilResponse_QNAME = new QName("http://servidor/", "consultarPerfilResponse");
    private final static QName _EliminarMensajeConfirmacionResponse_QNAME = new QName("http://servidor/", "eliminarMensajeConfirmacionResponse");
    private final static QName _ComercioComputadoraResponse_QNAME = new QName("http://servidor/", "comercioComputadoraResponse");
    private final static QName _RetornarMensajes_QNAME = new QName("http://servidor/", "retornarMensajes");
    private final static QName _RetornarCartasOfertadas_QNAME = new QName("http://servidor/", "retornarCartasOfertadas");
    private final static QName _RetornarMensajesResponse_QNAME = new QName("http://servidor/", "retornarMensajesResponse");
    private final static QName _PublicidadResponse_QNAME = new QName("http://servidor/", "publicidadResponse");
    private final static QName _FiltrarCartas_QNAME = new QName("http://servidor/", "filtrarCartas");
    private final static QName _GuardarResponse_QNAME = new QName("http://servidor/", "guardarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cliente
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetornarCartasOfertadasResponse }
     * 
     */
    public RetornarCartasOfertadasResponse createRetornarCartasOfertadasResponse() {
        return new RetornarCartasOfertadasResponse();
    }

    /**
     * Create an instance of {@link EliminarRespuesta }
     * 
     */
    public EliminarRespuesta createEliminarRespuesta() {
        return new EliminarRespuesta();
    }

    /**
     * Create an instance of {@link ComercioComputadora }
     * 
     */
    public ComercioComputadora createComercioComputadora() {
        return new ComercioComputadora();
    }

    /**
     * Create an instance of {@link CartaBean }
     * 
     */
    public CartaBean createCartaBean() {
        return new CartaBean();
    }

    /**
     * Create an instance of {@link ConsultarCartasSeleccionadasResponse }
     * 
     */
    public ConsultarCartasSeleccionadasResponse createConsultarCartasSeleccionadasResponse() {
        return new ConsultarCartasSeleccionadasResponse();
    }

    /**
     * Create an instance of {@link MensajesBean }
     * 
     */
    public MensajesBean createMensajesBean() {
        return new MensajesBean();
    }

    /**
     * Create an instance of {@link JugadorTO }
     * 
     */
    public JugadorTO createJugadorTO() {
        return new JugadorTO();
    }

    /**
     * Create an instance of {@link MandarSolicitudTrueque }
     * 
     */
    public MandarSolicitudTrueque createMandarSolicitudTrueque() {
        return new MandarSolicitudTrueque();
    }

    /**
     * Create an instance of {@link RetornaCarta }
     * 
     */
    public RetornaCarta createRetornaCarta() {
        return new RetornaCarta();
    }

    /**
     * Create an instance of {@link RetirarCartaOfertadaResponse }
     * 
     */
    public RetirarCartaOfertadaResponse createRetirarCartaOfertadaResponse() {
        return new RetirarCartaOfertadaResponse();
    }

    /**
     * Create an instance of {@link OfertarCartasResponse }
     * 
     */
    public OfertarCartasResponse createOfertarCartasResponse() {
        return new OfertarCartasResponse();
    }

    /**
     * Create an instance of {@link MandarSolicitudTruequeResponse }
     * 
     */
    public MandarSolicitudTruequeResponse createMandarSolicitudTruequeResponse() {
        return new MandarSolicitudTruequeResponse();
    }

    /**
     * Create an instance of {@link ConsultarCartasSeleccionadas }
     * 
     */
    public ConsultarCartasSeleccionadas createConsultarCartasSeleccionadas() {
        return new ConsultarCartasSeleccionadas();
    }

    /**
     * Create an instance of {@link Desconectar }
     * 
     */
    public Desconectar createDesconectar() {
        return new Desconectar();
    }

    /**
     * Create an instance of {@link ConsultarPerfil }
     * 
     */
    public ConsultarPerfil createConsultarPerfil() {
        return new ConsultarPerfil();
    }

    /**
     * Create an instance of {@link DesconectarResponse }
     * 
     */
    public DesconectarResponse createDesconectarResponse() {
        return new DesconectarResponse();
    }

    /**
     * Create an instance of {@link ConsultarPerfilResponse }
     * 
     */
    public ConsultarPerfilResponse createConsultarPerfilResponse() {
        return new ConsultarPerfilResponse();
    }

    /**
     * Create an instance of {@link Guardar }
     * 
     */
    public Guardar createGuardar() {
        return new Guardar();
    }

    /**
     * Create an instance of {@link ComercioComputadoraResponse }
     * 
     */
    public ComercioComputadoraResponse createComercioComputadoraResponse() {
        return new ComercioComputadoraResponse();
    }

    /**
     * Create an instance of {@link CartasOfertadasBean }
     * 
     */
    public CartasOfertadasBean createCartasOfertadasBean() {
        return new CartasOfertadasBean();
    }

    /**
     * Create an instance of {@link CartasDisponiblesBean }
     * 
     */
    public CartasDisponiblesBean createCartasDisponiblesBean() {
        return new CartasDisponiblesBean();
    }

    /**
     * Create an instance of {@link RetornarCartasOfertadasSistemas }
     * 
     */
    public RetornarCartasOfertadasSistemas createRetornarCartasOfertadasSistemas() {
        return new RetornarCartasOfertadasSistemas();
    }

    /**
     * Create an instance of {@link MensajeTrueque }
     * 
     */
    public MensajeTrueque createMensajeTrueque() {
        return new MensajeTrueque();
    }

    /**
     * Create an instance of {@link RetornarCartas }
     * 
     */
    public RetornarCartas createRetornarCartas() {
        return new RetornarCartas();
    }

    /**
     * Create an instance of {@link RetornarCartasOfertadas }
     * 
     */
    public RetornarCartasOfertadas createRetornarCartasOfertadas() {
        return new RetornarCartasOfertadas();
    }

    /**
     * Create an instance of {@link CartasDisponiblesTO }
     * 
     */
    public CartasDisponiblesTO createCartasDisponiblesTO() {
        return new CartasDisponiblesTO();
    }

    /**
     * Create an instance of {@link GuardarResponse }
     * 
     */
    public GuardarResponse createGuardarResponse() {
        return new GuardarResponse();
    }

    /**
     * Create an instance of {@link CartaTO }
     * 
     */
    public CartaTO createCartaTO() {
        return new CartaTO();
    }

    /**
     * Create an instance of {@link RetornarCartasOfertadasSistemasResponse }
     * 
     */
    public RetornarCartasOfertadasSistemasResponse createRetornarCartasOfertadasSistemasResponse() {
        return new RetornarCartasOfertadasSistemasResponse();
    }

    /**
     * Create an instance of {@link FiltrarCartasResponse }
     * 
     */
    public FiltrarCartasResponse createFiltrarCartasResponse() {
        return new FiltrarCartasResponse();
    }

    /**
     * Create an instance of {@link OfertarCartas }
     * 
     */
    public OfertarCartas createOfertarCartas() {
        return new OfertarCartas();
    }

    /**
     * Create an instance of {@link ComercioJugadoresResponse }
     * 
     */
    public ComercioJugadoresResponse createComercioJugadoresResponse() {
        return new ComercioJugadoresResponse();
    }

    /**
     * Create an instance of {@link EliminarRespuestaResponse }
     * 
     */
    public EliminarRespuestaResponse createEliminarRespuestaResponse() {
        return new EliminarRespuestaResponse();
    }

    /**
     * Create an instance of {@link PartidaBean }
     * 
     */
    public PartidaBean createPartidaBean() {
        return new PartidaBean();
    }

    /**
     * Create an instance of {@link RetornarMensajesResponse }
     * 
     */
    public RetornarMensajesResponse createRetornarMensajesResponse() {
        return new RetornarMensajesResponse();
    }

    /**
     * Create an instance of {@link RetornarCartasResponse }
     * 
     */
    public RetornarCartasResponse createRetornarCartasResponse() {
        return new RetornarCartasResponse();
    }

    /**
     * Create an instance of {@link RetornarMensajes }
     * 
     */
    public RetornarMensajes createRetornarMensajes() {
        return new RetornarMensajes();
    }

    /**
     * Create an instance of {@link Publicidad }
     * 
     */
    public Publicidad createPublicidad() {
        return new Publicidad();
    }

    /**
     * Create an instance of {@link Consultar }
     * 
     */
    public Consultar createConsultar() {
        return new Consultar();
    }

    /**
     * Create an instance of {@link RespuestaBean }
     * 
     */
    public RespuestaBean createRespuestaBean() {
        return new RespuestaBean();
    }

    /**
     * Create an instance of {@link EliminarMensajeConfirmacionResponse }
     * 
     */
    public EliminarMensajeConfirmacionResponse createEliminarMensajeConfirmacionResponse() {
        return new EliminarMensajeConfirmacionResponse();
    }

    /**
     * Create an instance of {@link ConsultarResponse }
     * 
     */
    public ConsultarResponse createConsultarResponse() {
        return new ConsultarResponse();
    }

    /**
     * Create an instance of {@link FiltrarCartas }
     * 
     */
    public FiltrarCartas createFiltrarCartas() {
        return new FiltrarCartas();
    }

    /**
     * Create an instance of {@link JugadorBean }
     * 
     */
    public JugadorBean createJugadorBean() {
        return new JugadorBean();
    }

    /**
     * Create an instance of {@link ByteArray }
     * 
     */
    public ByteArray createByteArray() {
        return new ByteArray();
    }

    /**
     * Create an instance of {@link RetornaCartaResponse }
     * 
     */
    public RetornaCartaResponse createRetornaCartaResponse() {
        return new RetornaCartaResponse();
    }

    /**
     * Create an instance of {@link EliminarMensajeConfirmacion }
     * 
     */
    public EliminarMensajeConfirmacion createEliminarMensajeConfirmacion() {
        return new EliminarMensajeConfirmacion();
    }

    /**
     * Create an instance of {@link RetirarCartaOfertada }
     * 
     */
    public RetirarCartaOfertada createRetirarCartaOfertada() {
        return new RetirarCartaOfertada();
    }

    /**
     * Create an instance of {@link PartidasJugadas }
     * 
     */
    public PartidasJugadas createPartidasJugadas() {
        return new PartidasJugadas();
    }

    /**
     * Create an instance of {@link ComercioJugadores }
     * 
     */
    public ComercioJugadores createComercioJugadores() {
        return new ComercioJugadores();
    }

    /**
     * Create an instance of {@link PublicidadResponse }
     * 
     */
    public PublicidadResponse createPublicidadResponse() {
        return new PublicidadResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MandarSolicitudTruequeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "mandarSolicitudTruequeResponse")
    public JAXBElement<MandarSolicitudTruequeResponse> createMandarSolicitudTruequeResponse(MandarSolicitudTruequeResponse value) {
        return new JAXBElement<MandarSolicitudTruequeResponse>(_MandarSolicitudTruequeResponse_QNAME, MandarSolicitudTruequeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FiltrarCartasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "filtrarCartasResponse")
    public JAXBElement<FiltrarCartasResponse> createFiltrarCartasResponse(FiltrarCartasResponse value) {
        return new JAXBElement<FiltrarCartasResponse>(_FiltrarCartasResponse_QNAME, FiltrarCartasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarRespuestaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "eliminarRespuestaResponse")
    public JAXBElement<EliminarRespuestaResponse> createEliminarRespuestaResponse(EliminarRespuestaResponse value) {
        return new JAXBElement<EliminarRespuestaResponse>(_EliminarRespuestaResponse_QNAME, EliminarRespuestaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComercioComputadora }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "comercioComputadora")
    public JAXBElement<ComercioComputadora> createComercioComputadora(ComercioComputadora value) {
        return new JAXBElement<ComercioComputadora>(_ComercioComputadora_QNAME, ComercioComputadora.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartasResponse")
    public JAXBElement<RetornarCartasResponse> createRetornarCartasResponse(RetornarCartasResponse value) {
        return new JAXBElement<RetornarCartasResponse>(_RetornarCartasResponse_QNAME, RetornarCartasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MandarSolicitudTrueque }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "mandarSolicitudTrueque")
    public JAXBElement<MandarSolicitudTrueque> createMandarSolicitudTrueque(MandarSolicitudTrueque value) {
        return new JAXBElement<MandarSolicitudTrueque>(_MandarSolicitudTrueque_QNAME, MandarSolicitudTrueque.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartasOfertadasSistemas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartasOfertadasSistemas")
    public JAXBElement<RetornarCartasOfertadasSistemas> createRetornarCartasOfertadasSistemas(RetornarCartasOfertadasSistemas value) {
        return new JAXBElement<RetornarCartasOfertadasSistemas>(_RetornarCartasOfertadasSistemas_QNAME, RetornarCartasOfertadasSistemas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComercioJugadoresResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "comercioJugadoresResponse")
    public JAXBElement<ComercioJugadoresResponse> createComercioJugadoresResponse(ComercioJugadoresResponse value) {
        return new JAXBElement<ComercioJugadoresResponse>(_ComercioJugadoresResponse_QNAME, ComercioJugadoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Publicidad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "publicidad")
    public JAXBElement<Publicidad> createPublicidad(Publicidad value) {
        return new JAXBElement<Publicidad>(_Publicidad_QNAME, Publicidad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComercioJugadores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "comercioJugadores")
    public JAXBElement<ComercioJugadores> createComercioJugadores(ComercioJugadores value) {
        return new JAXBElement<ComercioJugadores>(_ComercioJugadores_QNAME, ComercioJugadores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornaCartaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornaCartaResponse")
    public JAXBElement<RetornaCartaResponse> createRetornaCartaResponse(RetornaCartaResponse value) {
        return new JAXBElement<RetornaCartaResponse>(_RetornaCartaResponse_QNAME, RetornaCartaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPerfil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultarPerfil")
    public JAXBElement<ConsultarPerfil> createConsultarPerfil(ConsultarPerfil value) {
        return new JAXBElement<ConsultarPerfil>(_ConsultarPerfil_QNAME, ConsultarPerfil.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Guardar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "guardar")
    public JAXBElement<Guardar> createGuardar(Guardar value) {
        return new JAXBElement<Guardar>(_Guardar_QNAME, Guardar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarCartasSeleccionadasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultarCartasSeleccionadasResponse")
    public JAXBElement<ConsultarCartasSeleccionadasResponse> createConsultarCartasSeleccionadasResponse(ConsultarCartasSeleccionadasResponse value) {
        return new JAXBElement<ConsultarCartasSeleccionadasResponse>(_ConsultarCartasSeleccionadasResponse_QNAME, ConsultarCartasSeleccionadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarMensajeConfirmacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "eliminarMensajeConfirmacion")
    public JAXBElement<EliminarMensajeConfirmacion> createEliminarMensajeConfirmacion(EliminarMensajeConfirmacion value) {
        return new JAXBElement<EliminarMensajeConfirmacion>(_EliminarMensajeConfirmacion_QNAME, EliminarMensajeConfirmacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarRespuesta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "eliminarRespuesta")
    public JAXBElement<EliminarRespuesta> createEliminarRespuesta(EliminarRespuesta value) {
        return new JAXBElement<EliminarRespuesta>(_EliminarRespuesta_QNAME, EliminarRespuesta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DesconectarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "desconectarResponse")
    public JAXBElement<DesconectarResponse> createDesconectarResponse(DesconectarResponse value) {
        return new JAXBElement<DesconectarResponse>(_DesconectarResponse_QNAME, DesconectarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartasOfertadasSistemasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartasOfertadasSistemasResponse")
    public JAXBElement<RetornarCartasOfertadasSistemasResponse> createRetornarCartasOfertadasSistemasResponse(RetornarCartasOfertadasSistemasResponse value) {
        return new JAXBElement<RetornarCartasOfertadasSistemasResponse>(_RetornarCartasOfertadasSistemasResponse_QNAME, RetornarCartasOfertadasSistemasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartas")
    public JAXBElement<RetornarCartas> createRetornarCartas(RetornarCartas value) {
        return new JAXBElement<RetornarCartas>(_RetornarCartas_QNAME, RetornarCartas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartasOfertadasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartasOfertadasResponse")
    public JAXBElement<RetornarCartasOfertadasResponse> createRetornarCartasOfertadasResponse(RetornarCartasOfertadasResponse value) {
        return new JAXBElement<RetornarCartasOfertadasResponse>(_RetornarCartasOfertadasResponse_QNAME, RetornarCartasOfertadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarCartasSeleccionadas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultarCartasSeleccionadas")
    public JAXBElement<ConsultarCartasSeleccionadas> createConsultarCartasSeleccionadas(ConsultarCartasSeleccionadas value) {
        return new JAXBElement<ConsultarCartasSeleccionadas>(_ConsultarCartasSeleccionadas_QNAME, ConsultarCartasSeleccionadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetirarCartaOfertada }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retirarCartaOfertada")
    public JAXBElement<RetirarCartaOfertada> createRetirarCartaOfertada(RetirarCartaOfertada value) {
        return new JAXBElement<RetirarCartaOfertada>(_RetirarCartaOfertada_QNAME, RetirarCartaOfertada.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultarResponse")
    public JAXBElement<ConsultarResponse> createConsultarResponse(ConsultarResponse value) {
        return new JAXBElement<ConsultarResponse>(_ConsultarResponse_QNAME, ConsultarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Desconectar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "desconectar")
    public JAXBElement<Desconectar> createDesconectar(Desconectar value) {
        return new JAXBElement<Desconectar>(_Desconectar_QNAME, Desconectar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consultar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultar")
    public JAXBElement<Consultar> createConsultar(Consultar value) {
        return new JAXBElement<Consultar>(_Consultar_QNAME, Consultar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertarCartas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ofertarCartas")
    public JAXBElement<OfertarCartas> createOfertarCartas(OfertarCartas value) {
        return new JAXBElement<OfertarCartas>(_OfertarCartas_QNAME, OfertarCartas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetirarCartaOfertadaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retirarCartaOfertadaResponse")
    public JAXBElement<RetirarCartaOfertadaResponse> createRetirarCartaOfertadaResponse(RetirarCartaOfertadaResponse value) {
        return new JAXBElement<RetirarCartaOfertadaResponse>(_RetirarCartaOfertadaResponse_QNAME, RetirarCartaOfertadaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertarCartasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ofertarCartasResponse")
    public JAXBElement<OfertarCartasResponse> createOfertarCartasResponse(OfertarCartasResponse value) {
        return new JAXBElement<OfertarCartasResponse>(_OfertarCartasResponse_QNAME, OfertarCartasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornaCarta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornaCarta")
    public JAXBElement<RetornaCarta> createRetornaCarta(RetornaCarta value) {
        return new JAXBElement<RetornaCarta>(_RetornaCarta_QNAME, RetornaCarta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarPerfilResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "consultarPerfilResponse")
    public JAXBElement<ConsultarPerfilResponse> createConsultarPerfilResponse(ConsultarPerfilResponse value) {
        return new JAXBElement<ConsultarPerfilResponse>(_ConsultarPerfilResponse_QNAME, ConsultarPerfilResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarMensajeConfirmacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "eliminarMensajeConfirmacionResponse")
    public JAXBElement<EliminarMensajeConfirmacionResponse> createEliminarMensajeConfirmacionResponse(EliminarMensajeConfirmacionResponse value) {
        return new JAXBElement<EliminarMensajeConfirmacionResponse>(_EliminarMensajeConfirmacionResponse_QNAME, EliminarMensajeConfirmacionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComercioComputadoraResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "comercioComputadoraResponse")
    public JAXBElement<ComercioComputadoraResponse> createComercioComputadoraResponse(ComercioComputadoraResponse value) {
        return new JAXBElement<ComercioComputadoraResponse>(_ComercioComputadoraResponse_QNAME, ComercioComputadoraResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarMensajes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarMensajes")
    public JAXBElement<RetornarMensajes> createRetornarMensajes(RetornarMensajes value) {
        return new JAXBElement<RetornarMensajes>(_RetornarMensajes_QNAME, RetornarMensajes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarCartasOfertadas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarCartasOfertadas")
    public JAXBElement<RetornarCartasOfertadas> createRetornarCartasOfertadas(RetornarCartasOfertadas value) {
        return new JAXBElement<RetornarCartasOfertadas>(_RetornarCartasOfertadas_QNAME, RetornarCartasOfertadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetornarMensajesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "retornarMensajesResponse")
    public JAXBElement<RetornarMensajesResponse> createRetornarMensajesResponse(RetornarMensajesResponse value) {
        return new JAXBElement<RetornarMensajesResponse>(_RetornarMensajesResponse_QNAME, RetornarMensajesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicidadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "publicidadResponse")
    public JAXBElement<PublicidadResponse> createPublicidadResponse(PublicidadResponse value) {
        return new JAXBElement<PublicidadResponse>(_PublicidadResponse_QNAME, PublicidadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FiltrarCartas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "filtrarCartas")
    public JAXBElement<FiltrarCartas> createFiltrarCartas(FiltrarCartas value) {
        return new JAXBElement<FiltrarCartas>(_FiltrarCartas_QNAME, FiltrarCartas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GuardarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "guardarResponse")
    public JAXBElement<GuardarResponse> createGuardarResponse(GuardarResponse value) {
        return new JAXBElement<GuardarResponse>(_GuardarResponse_QNAME, GuardarResponse.class, null, value);
    }

}
